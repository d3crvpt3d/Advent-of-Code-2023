#include <iostream>
#include <list>
#include <vector>
using namespace std;


void transpose(vector<vector<char>> *block){

    vector<vector<char>> block_t;

    for(uint16_t i = 0; i < (*block).at(0).size(); i++){
        block_t.push_back({});
        for(uint16_t j = 0; j < (*block).size(); j++){
            block_t.at(i).push_back({});
        }
    }

    //DEBUG
    for(vector<char> s : (*block)){
        for (char c : s){
            cerr << c;
        }
        cerr << "\n";
    }

    cerr << "y" << (*block).size() << "\n";
    cerr << "x" << (*block).at(0).size() << "\n";

    cerr << "y" << block_t.size() << "\n";
    cerr << "x" << block_t.at(0).size() << "\n";


    for(int a = 0; a < (*block).size(); a++){
        for(int b = 0; b < (*block).at(0).size(); b++){
            block_t.at(b).at(a) = (*block).at(a).at(b);
        }
    }

    //write transposed
    block = &block_t;

    return;
}

int getHori(vector<vector<char>> *block){
    return 0;
}

int first(vector<vector<vector<char>>> *input){

    int sum = 0;

    //vertical
    for(uint16_t i = 0; i < (*input).size(); i++){
        int out = 100*getHori(&(*input).at(i));
        if(out != 0){
            sum += out;
        }else{
            transpose(&(*input).at(i));
            sum += getHori(&(*input).at(i));
        }
    }

    return sum;
}

int second(vector<vector<vector<char>>> *input){
    return 0;
}

int main(int argc, char **argv){

    vector<vector<vector<char>>> stringVector;
    stringVector.push_back({}); // add first block

    uint16_t counter = 0;
    while (!cin.eof()){
        string s;
        getline(cin, s);
        
        //wtf is this string size
        if(s.size() <= 1){
            stringVector.push_back({}); // add new block
            counter++;
            continue;
        }

        //convert string to array

        vector<char>

        vector<char> vec(s.begin(), s.);

        //replace delimiters
        if(){

        }

        stringVector.at(counter).push_back(vec);

    }

    cerr << stringVector.at(1).at(5).size() << "\n";
    cerr << stringVector.at(1).at(6).size() << "\n";

    //print outputs
    cout << "First: \n" << first(&stringVector) << "\n";

    
    cout << "Second: \n" << second(&stringVector) << "\n";

    return 0;
}