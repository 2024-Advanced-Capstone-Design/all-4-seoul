from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import csv

url = 'https://map.kakao.com/'
driver = webdriver.Chrome()  # 드라이버 경로
driver.get(url)

searchloc = ['서초구 맛집',  '강동구 맛집']

#'종로구 맛집', '중구 맛집', '용산구 맛집', '성동구 맛집', '광진구 맛집', '동대문구 맛집', '중랑구 맛집', '성북구 맛집', '강북구 맛집', '도봉구 맛집', '노원구 맛집', '은평구 맛집', '서대문구 맛집', '마포구 맛집', '양천구 맛집', '강서구 맛집', '구로구 맛집', '금천구 맛집', '영등포구 맛집', '동작구 맛집', '관악구 맛집', '서초구 맛집', '강남구 맛집', '송파구 맛집', '강동구 맛집'
#', 
for loc in searchloc:
    # 음식점 입력 후 찾기 버튼 클릭 
    search_area = driver.find_element(By.XPATH, '//*[@id="search.keyword.query"]')   # 검색창
    search_area.clear()  # 검색창 내용 지우기
    search_area.send_keys(loc)
    driver.find_element(By.XPATH, '//*[@id="search.keyword.submit"]').send_keys(Keys.ENTER)
    time.sleep(2)

    # 장소 버튼 클릭 
    driver.find_element(By.XPATH, '//*[@id="info.main.options"]/li[2]/a').send_keys(Keys.ENTER)

    def storeNamePrint(page):
        time.sleep(0.2)

        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')

        store_lists = soup.select('.placelist > .PlaceItem')
        list = []

        for store in store_lists:
            temp = []
            name = store.select('.head_item > .tit_name > .link_name')[0].text
            degree = store.select('.rating > .score > .num')[0].text
            addr = store.select('.info_item > .addr')[0].text.splitlines()[1]  # 도로명주소 
            tel = store.select('.info_item > .contact > .phone')[0].text

            print(name, degree, addr, tel, '-')

            temp.append(name)
            temp.append(degree)
            temp.append(addr)
            temp.append(tel)

            list.append(temp)

        if page == 1:
            with open('store_list_{}.csv'.format(loc), 'w', encoding='utf-8-sig', newline='') as f:
                writercsv = csv.writer(f)
                header = ['name', 'degree', 'address', 'tel']
                writercsv.writerow(header)

                for i in list:
                    writercsv.writerow(i)
        else:   
            # 파일이 이미 존재하므로, 존재하는 파일에 이어서 쓰기 
            with open('store_list_{}.csv'.format(loc), 'a', encoding='utf-8-sig', newline='') as f:
                writercsv = csv.writer(f)

                for i in list:
                    writercsv.writerow(i)

    
    storeNamePrint(1)
    while True:
        next_page_btn = driver.find_element(By.XPATH, '//*[@id="info.search.page.next"]')
        if "disabled" in next_page_btn.get_attribute("class"):
            break
        
        try:
            # 장소 더보기 버튼 누르기 
            btn = driver.find_element(By.CSS_SELECTOR, '.more')   
            driver.execute_script("arguments[0].click();", btn)

            for i in range(2, 6):
                # 페이지 넘기기
                xPath = '//*[@id="info.search.page.no' + str(i) + '"]'
                driver.find_element(By.XPATH, xPath).send_keys(Keys.ENTER)
                time.sleep(1)

                storeNamePrint(i)
            next_page_btn.send_keys(Keys.ENTER)
        except:
            print('ERROR!')

print('**크롤링 완료**')
