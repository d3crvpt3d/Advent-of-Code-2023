#include <iostream>
#include <list>
using namespace std;

int main(int argc, char *argv[]){

    std::list<string> stringList = {"start:\n"};

    while (!cin.eof()){
        string buf;
        getline(cin, buf);
        stringList.push_back(buf);
    }
    
    
    while(!stringList.empty()){
        cout << stringList.front();
        stringList.pop_front();
    }

    return 0;
}