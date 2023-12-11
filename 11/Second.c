#include <stdio.h>
#include <stdlib.h>

struct Galaxy {
    int x;
    int y;
    long pathLength;
    int* filledZeiles;
    int* filledSpalte;
    int extra;
    struct Galaxy* b;
};

void fillExtraDistanceZeile(char** sList, int size, int* bArr) {
    for (int i = 0; i < size; i++) {
        char* aL = sList[i];
        for (int j = 0; j < sizeof(aL); j++) {
            if (aL[j] == '#') {
                bArr[i] = 1;
            }
        }
    }
}

void fillExtraDistanceSpalte(char** sList, int size, int* bArr) {
    for (int i = 0; i < size; i++) {
        char* aL = sList[i];
        for (int j = 0; j < sizeof(aL); j++) {
            if (aL[j] == '#') {
                bArr[j] = 1;
            }
        }
    }
}

void pathLength(struct Galaxy* galaxy) {
    int xmin;
    int xmax;
    int ymin;
    int ymax;
    if (galaxy->x - galaxy->b->x > 0) {
        xmin = galaxy->b->x;
        xmax = galaxy->x;
    } else {
        xmin = galaxy->x;
        xmax = galaxy->b->x;
    }
    if (galaxy->y - galaxy->b->y > 0) {
        ymin = galaxy->b->y;
        ymax = galaxy->y;
    } else {
        ymin = galaxy->y;
        ymax = galaxy->b->y;
    }
    int lücken = 0;

    for (int x = xmin + 1; x < xmax; x++) {
        if (!galaxy->filledSpalte[x]) {
            lücken++;
        }
    }

    for (int y = ymin + 1; y < ymax; y++) {
        if (!galaxy->filledZeiles[y]) {
            lücken++;
        }
    }
    galaxy->pathLength += xmax - xmin + ymax - ymin + lücken * galaxy->extra - lücken;
}

int main() {
    int extra = 1000000;
    int filledZeiles[1000];
    int filledSpalte[1000];
    char* space[1000];
    int spaceSize = 0;

    char line[1000];
    while (fgets(line, sizeof(line), stdin)) {
        char* cArr = malloc(sizeof(line));
        for (int i = 0; i < sizeof(line); i++) {
            cArr[i] = line[i];
        }
        space[spaceSize] = cArr;
        spaceSize++;
    }

    fillExtraDistanceZeile(space, spaceSize, filledZeiles);
    fillExtraDistanceSpalte(space, spaceSize, filledSpalte);

    struct Galaxy galaxyList[1000];
    int galaxyListSize = 0;
    for (int y = 0; y < spaceSize; y++) {
        char* a = space[y];
        for (int x = 0; x < sizeof(a); x++) {
            char c = a[x];
            if (c == '#') {
                struct Galaxy galaxy;
                galaxy.x = x;
                galaxy.y = y;
                galaxy.pathLength = 0;
                galaxy.filledZeiles = filledZeiles;
                galaxy.filledSpalte = filledSpalte;
                galaxy.extra = extra;
                galaxyList[galaxyListSize] = galaxy;
                galaxyListSize++;
            }
        }
    }

    for (int i = 0; i < galaxyListSize; i++) {
        struct Galaxy* g1 = &galaxyList[i];
        for (int j = i + 1; j < galaxyListSize; j++) {
            g1->b = &galaxyList[j];
            pathLength(g1);
        }
    }

    long out = 0;
    for (int i = 0; i < galaxyListSize; i++) {
        struct Galaxy galaxy = galaxyList[i];
        out += galaxy.pathLength;
    }
    printf("%ld\n", out);

    return 0;
}