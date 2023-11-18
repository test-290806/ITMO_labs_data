import json
from dicttoxml import dicttoxml
from xml.dom.minidom import parseString

def JSON2XML1(inputFile, outputFile):
    # Открываем son файл
    f = open(inputFile, encoding='utf8')
    # Преобразуем его в словарь
    json_obj = json.load(f)
    # Получаем строку xml
    xml_obj = dicttoxml(json_obj, attr_type=False)
    # Открыли файл на запись
    output_file = open(outputFile, "w", encoding='utf8')
    # Получем отворматированную строку
    parsed_string = parseString(xml_obj).toprettyxml()
    # Записываем в файл
    output_file.write(parsed_string)
def main():
    JSON2XML1('day.json', 'day1.xml')

if __name__ == '__main__':
    main()