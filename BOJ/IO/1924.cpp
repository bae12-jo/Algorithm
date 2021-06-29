/*

main 함수가 최대한 간단하도록 class 를 활용한다

전역 변수는 private 으로 선언하여 임의의 접근 및 수정을 방지하고

사용자 입력 및 출력이 필요한 최소한의 부분만 public 으로 선언한다

*/

#include <iostream>
#include <string>
using namespace std;

class Calendar{
	private:
	int month[12]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 월별 일수 세팅
	string day[7]={"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}; // 요일별 문자열값 세팅
	int m, d, count;
	
	public:
	void input(){
		cin>>m;
		cin>>d;
		count=0;
	}
	void getDay(){
		for(int i=0;i<m-1;i++) d+=month[i]; // 전월까지 월별 일수를 더해야 함
		count=d%7;
		cout<<day[count];
	}
};

int main(void)
{
	Calendar c;
    c.input();
    c.getDay();
    return 0;
}