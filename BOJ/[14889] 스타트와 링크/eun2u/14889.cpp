#include <iostream>
#include <cstring>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

int N;
int arr[20][20];
bool visited[20];
int minResult = 987654321;
void calculAbility(vector<int> picked) {
	vector<int> another;
	bool check[20];
	memset(check, false, sizeof(check));

	for (int i = 0; i < picked.size(); i++)
		check[picked[i]] = true;
	for (int i = 0; i < N; i++) {
		if (!check[i]) {
			another.push_back(i);
		}
	}

	int sTeam = 0, lTeam = 0;
	for (int i = 0; i < picked.size(); i++) {
		for (int j = i + 1; j < picked.size(); j++) {
			sTeam += arr[picked[i]][picked[j]];
			sTeam += arr[picked[j]][picked[i]];
		}
	}
	for (int i = 0; i < another.size(); i++) {
		for (int j = i + 1; j < another.size(); j++) {
			lTeam += arr[another[i]][another[j]];
			lTeam += arr[another[j]][another[i]];
		}
	}

	minResult = min(minResult, abs(sTeam - lTeam));
}
void pickS(vector<int> picked, int toPick) {
	if (toPick == 0) {
		calculAbility(picked);
		return;
	}
	int smallest = picked.empty() ? 0 : picked.back() + 1;

	for (int next = smallest; next < N; next++) {
		picked.push_back(next);
		pickS(picked, toPick - 1);
		picked.pop_back();
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> arr[i][j];

	vector<int> picked;
	memset(visited, false, sizeof(visited));
	pickS(picked, N / 2);
	cout << minResult << '\n';
	return 0;
}