#include <iostream>
#include <stack>
#include <string>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    cin.ignore();
    // cin�� \n�� ������ ���� �ʴ´� (�Է¹��ۿ� �����ش�) 
    // getline�� \n�� ������ ��´� (�Է¹��ۿ� ���ܵ��� �ʴ´�)
    // cin.ignore()�� ������ �� ���� ���� �ϳ��� �����
    // ���⼭�� getline�� ������� �����Ͽ� ù ���ڿ��� ������ �Ǵ� ���� ���� ���� ���
    // (1) cin�� getline�� ���޾� ����� �� ���۸� ����ֱ� ���� ignore�� ����Ѵ�
    // (2) cin ���� cin �� ���� ��� �� ���ۿ� �ִ� ����� ������ �����Ѵ�
    // (3) getline ���� getline�� ���� ��� \n�� ���ۿ� ���Խ�Ű�� �ʱ� ������ ��� �ʿ䰡 ����
    while (n--) {
        string str;
        getline(cin, str);
        str += '\n'; // ���ڿ� �ڿ� ���๮�� ���Ƿ� �־��ֱ�
        stack<char> s;
        for (char ch : str){ // range based for ��
        // for (������ Ÿ�� elem : ������ ����Ʈ)
        // ���۰� ���� ������� �ʾƵ� ����Ʈ�� ó������ ������ ��ȸ���ִ� �ݺ���
            if (ch==' '|| ch=='\n'){ // �����̳� ������ ������ �������� ���
                while(!s.empty()){
                    cout<<s.top();
                    s.pop();
                }
                cout << ch; // ���� ���� �Ǵ� ������ ���
            }else{
                s.push(ch); // �ܾ�� ���ÿ� �־��ֱ�
            }
        }
    }
    return 0;
}