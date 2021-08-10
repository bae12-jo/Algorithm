#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

bool compare(pair<int, int> a, pair<int, int> b){
    if (a.first == b.first)
        return a.second < b.second;
    return a.first < b.first;
}

int main(){

    int num, x, y;
    vector<pair<int, int>> vec;
    cin >> num;
    for(int i=0; i<num; i++){
        cin >> x >> y;
        vec.push_back(make_pair(x, y));
    }

    sort(vec.begin(), vec.end(), compare);

    for(int i=0; i<num; i++){
        cout << vec[i].first << ' ' << vec[i].second << '\n';
    }

    return 0;
}