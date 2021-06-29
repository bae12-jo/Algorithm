#include <iostream>
using namespace std;

int main(void)
{
    int n;
    cin>>n;
    for(int i=n-1;i>=0;i--)
    {
        for(int j=i-1;j>=0;j--)
        {
        	cout<<" ";
        }
        for(int k=n-i;k>0;k--)
        {
        	cout<<"* ";
        }
        cout<<endl;
    }
    return 0;
}