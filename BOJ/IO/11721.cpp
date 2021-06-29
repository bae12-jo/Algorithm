/* string 사용 */

#include <iostream>
#include <string>
using namespace std;

int main(void)
{
    string str;
    cin>>str;
    int i=0;
    while(true){
    	cout<<str.substr(i, 10)<<endl;
    	i+=10;
    	if (i>str.length()-1) break;
    }
    return 0;
}

/* char 배열 사용 */

#include <iostream>
using namespace std;

int main(void)
{
    char c[101];
    cin>>c;
    for(int i=0;i<sizeof(c);i++){
    	if(c[i]=='\0') break;
    	cout<<c[i];
    	if((i+1)%10==0) cout<<endl;
    }
    return 0;
}