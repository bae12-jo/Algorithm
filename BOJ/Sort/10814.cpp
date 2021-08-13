#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

struct Person{
    int age;
    string name;
    int join_date;
};

bool compare(Person a, Person b){
    if (a.age == b.age)
        return a.join_date < b.join_date;
    return a.age < b.age;
}

int main(){

    int num, x, y;
    cin >> num;
    vector<Person> vec(num);
    for(int i=0; i<num; i++){
        cin >> vec[i].age >> vec[i].name;
        vec[i].join_date = i;
    }

    sort(vec.begin(), vec.end(), compare);

    for(int i=0; i<num; i++){
        cout << vec[i].age << ' ' << vec[i].name << '\n';
    }

    return 0;
}