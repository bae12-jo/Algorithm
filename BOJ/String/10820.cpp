#include <iostream>
#include <string>

using namespace std;

int main(){

    string str;

    while(true){

    getline(cin, str);
    if(str.size() == 0) break;
        
    int low=0, upp=0, num=0, blnk=0;

    for(int i=0; i<str.size(); i++){
        if(str[i]>=97 && str[i]<=122) low+=1;
        else if(str[i]>=65 && str[i]<=90) upp+=1;
        else if(str[i]>=48 && str[i] <=57) num+=1;
        else if(str[i]==32) blnk+=1;
    }

    cout << low << " " << upp << " " << num << " " << blnk << '\n';

    }

}