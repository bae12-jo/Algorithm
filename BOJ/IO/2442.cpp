#include <iostream>
using namespace std;

int main() {
	int n;
	cin>>n;
	for(int i=1;i<=n;i++){
		for(int j=0;j<((2*n-1)-(2*i-1))/2;j++) cout<<" ";
		for(int k=1;k<=2*i-1;k++) cout<<"*";
		cout<<endl;
	}
	return 0;
}