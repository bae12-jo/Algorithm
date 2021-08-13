#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

struct Student{
    int korean, english, math;
    string name;
};

bool compare(Student a, Student b){
    if (a.korean != b.korean){
        return a.korean < b.korean;
    }
    else if(a.korean == b.korean && a.english != b.english){
        return a.english < b.english;
    }
    else if(a.korean == b.korean && a.english == b.english && a.math != b.math){
        return a.math < b.math;
    }
    else{
        return a.name < b.name;
    }
}

int main(){
    int num, korean, english, math;
    string name;

    cin >> num;
    vector<Student> vec(num);

    for(int i=0; i<num; i++){
        cin >> vec[i].name >> vec[i].korean >> vec[i].english >> vec[i].math;
    }

    sort(vec.begin(), vec.end(), compare);

    for(int i=0; i<num; i++){
        cout << vec[i].name << '\n';
    }

    return 0;
}