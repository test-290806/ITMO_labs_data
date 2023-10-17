#include <iostream>
#include <string>
using namespace std;

bool check(string s) {
	//Проверка на размер строки
	if (s.size() != 7) return false;
	//Проверка на посторонние символы
	for (auto i : s) if (i != '1' && i != '0') return false;

	return true;
}

int main()
{
	//Читаем набор входных данных как строку
	string b;
	cin >> b;

	if (!check(b)) {
		cout << "Wrong input!";
		return 0;
	}

	//Преобразуем в массив из 0 и 1
	int a[7];
	for (int i = 0; i < 7; i++) a[i] = b[i] - '0';

/*
		0	1	2	3	4	5	6
	2^x	r1	r2	i1	r3	i2	i3	i4
	1	X		X		X		X
	2		X	X			X	X
	4				X	X	X	X
*/
	//Извлекаем биты четности и информационный биты в массивы
	int i[4];
	i[0] = a[2];
	i[1] = a[4];
	i[2] = a[5];
	i[3] = a[6];

	int r[3];
	r[0] = a[0];
	r[1] = a[1];
	r[2] = a[3];

	//Вычисляем синдромы последовательности
	int s1 = (r[0] + i[0] + i[1] + i[3]) % 2;
	int s2 = (r[1] + i[0] + i[2] + i[3]) % 2;
	int s3 = (r[2] + i[1] + i[2] + i[3]) % 2;

	//Создаем словарь для декодирования значений синдромов в биты ошибок
	// Здесь индексы от 0 до 8 => закодированный значения троек синдромов
	// Словарь состтоит из пар => первое значение - 'r' или 'i', второе - номер бита
	pair<char, int> d[8] = {
		{'-', -1},
		{'r', 2},
		{'r', 1},
		{'i', 2},
		{'r', 0},
		{'i', 1},
		{'i', 0},
		{'i', 3}
	};

	//Переводим значения синдромов в число, для получения индекса в словаре
	int j = s1 * 4 + s2 * 2 + s3 * 1;
	
	//Запоминаем бит с ошибкой и сиправялем её она есть 
	string errBit = "-";
	if (d[j].first != '-') {
		errBit = d[j].first + to_string(d[j].second + 1);
		
		if (d[j].first == 'i') i[d[j].second] = (i[d[j].second] + 1) % 2;
		if (d[j].first == 'r') r[d[j].second] = (r[d[j].second] + 1) % 2;
	}

	//Выводим ответ
	cout << "Correct bits: ";
	for (int k : i) cout << k;
	cout << endl;

	//Выводи ошибки если они есть
	if (errBit != "") {
		cout << "Error in bit: " << errBit << endl;
	}
	else {
		cout << "No errors" << endl;
	}
	cout << r[0] << r[1] << i[0] << r[2] << i[1] << i[2] << i[3] << endl;
	return 0;
}