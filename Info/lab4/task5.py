import pandas as pd
import json
def main():
    with open('day.json', mode='r', encoding='utf-8') as f:
        json_data = json.load(f)
    df = pd.json_normalize(json_data)
    df.to_csv('day5.csv', encoding='utf8', index=False)

if __name__ == '__main__':
    main()