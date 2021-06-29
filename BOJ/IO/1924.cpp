/*

main �Լ��� �ִ��� �����ϵ��� class �� Ȱ���Ѵ�

���� ������ private ���� �����Ͽ� ������ ���� �� ������ �����ϰ�

����� �Է� �� ����� �ʿ��� �ּ����� �κи� public ���� �����Ѵ�

*/

#include <iostream>
#include <string>
using namespace std;

class Calendar{
	private:
	int month[12]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // ���� �ϼ� ����
	string day[7]={"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}; // ���Ϻ� ���ڿ��� ����
	int m, d, count;
	
	public:
	void input(){
		cin>>m;
		cin>>d;
		count=0;
	}
	void getDay(){
		for(int i=0;i<m-1;i++) d+=month[i]; // �������� ���� �ϼ��� ���ؾ� ��
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