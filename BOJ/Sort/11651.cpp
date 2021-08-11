#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int, int> a, pair<int, int> b){
    if (a.second < b.second)
        return true;
    else if(a.second == b.second)
    {
        if (a.first < b.first)
            return true;
    }
    return false;
}

int main(){

    int num, x, y;
    cin >> num;

    vector<pair<int, int>> vec;
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