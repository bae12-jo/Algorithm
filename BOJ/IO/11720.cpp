/* char 배열 사용 */

#include <iostream>
using namespace std;

int main(void)
{
    int n, sum=0;
    cin>>n;
    char c[n];
    for(int i=0;i<n;i++){
    	cin>>c[i];
    	sum+=(c[i]-'0');
    }
    cout<<sum;
    return 0;
}


/* char 사용 */

#include <iostream>
using namespace std;

int main(void)
{
    int n, sum=0;
    cin>>n;
    char c;
    for(int i=0;i<n;i++){
    	cin>>c;
    	sum+=(c-'0');
    }
    cout<<sum;
    return 0;
}