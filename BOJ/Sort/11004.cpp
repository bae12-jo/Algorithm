#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


int main(){

    cin.tie(0);
    cin.sync_with_stdio(false);

    int num, key;
    cin >> num >> key;

    vector<int> vec(num);

    for(int i=0; i<num; i++){
        cin >> vec[i];
    }

    sort(vec.begin(), vec.end());

    cout << vec[key-1];

}