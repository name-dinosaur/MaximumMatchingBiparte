#include <iostream>
#include <vector>
#define MAX 101

using namespace std;

vector<int> vtx[MAX];
int slt[MAX];
bool done[MAX];
int n = 5;

// DFS
bool dfs(int x) {
    //try if they can fit in
    for (int i = 0; i < vtx[x].size(); i++) {
        int p = vtx[x][i];
        //dont consider anything that has been read through
        if (done[p]) continue;
        done[p] = true;
        //check if it is empty or has more space
        if (slt[p] == 0 || dfs(slt[p])) {
            slt[p] = x;
            return true;
        }
    }
    return false;
}

int main() {
    vtx[1].push_back(1);
    vtx[1].push_back(3);
    vtx[1].push_back(5);
    vtx[2].push_back(1);
    vtx[2].push_back(2);
    vtx[3].push_back(5);
    vtx[4].push_back(3);
    vtx[5].push_back(2);
    int cnt = 0; //successful matchings
    for (int i = 1; i <= n; i++) {
        fill(done, done + MAX, false);
        if (dfs(i)) cnt++;
    }
    cout << cnt << " matches successful\n";
    for (int i = 1; i < MAX; i++) {
        if (slt[i] != 0) {
            cout << slt[i] << " => " << i << "\n";
        }
    }
    return 0;
}