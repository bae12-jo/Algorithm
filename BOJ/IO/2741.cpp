/* for문 */

#include <iostream>
using namespace std;

int main(void)
{
    int n;
    cin>>n;
    for(int i=0;i<n;i++) cout<<i+1<<'\n'; //endl을 쓰면 시간초과
    return 0;
}

/* while문 */

#include <iostream>
using namespace std;

int main(void)
{
    int n, i=1;
    cin>>n;
    while(true){
    	cout<<i++<<'\n'; // endl을 쓰면 시간초과
    	if(i>n) break;
    	
    }
    return 0;
}

/*

처음에 시간초과가 떴는데 문제는 반복문이 아니고 개행문자였다

본 문제에서는 아니었지만 cin, cout을 써서 시간초과가 뜨는 경우도 있으니

연산속도가 중요한 문제에서는 cstdio 를 쓰는게 마음 편할 것 같다

*/