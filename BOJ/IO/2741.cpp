/* for�� */

#include <iostream>
using namespace std;

int main(void)
{
    int n;
    cin>>n;
    for(int i=0;i<n;i++) cout<<i+1<<'\n'; //endl�� ���� �ð��ʰ�
    return 0;
}

/* while�� */

#include <iostream>
using namespace std;

int main(void)
{
    int n, i=1;
    cin>>n;
    while(true){
    	cout<<i++<<'\n'; // endl�� ���� �ð��ʰ�
    	if(i>n) break;
    	
    }
    return 0;
}

/*

ó���� �ð��ʰ��� ���µ� ������ �ݺ����� �ƴϰ� ���๮�ڿ���

�� ���������� �ƴϾ����� cin, cout�� �Ἥ �ð��ʰ��� �ߴ� ��쵵 ������

����ӵ��� �߿��� ���������� cstdio �� ���°� ���� ���� �� ����

*/