#include <iostream>

int main(void)
{
    int n, a, b;
    char comma;
    std::cin>>n;
    for(int i=0;i<n;i++)
    {
    	std::cin>>a>>comma>>b;
    	std::cout<<a+b<<std::endl;
	}
    return 0;
}