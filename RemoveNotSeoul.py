import pandas as pd

# CSV 파일 경로
file_path = 'D:/대학교/2024-1학기/심화캡스톤/Crawling/카페 크롤링/카페.csv'

# CSV 파일 읽기
df = pd.read_csv(file_path)

# 중복된 행 제거
df_unique = df.drop_duplicates()

# NaN 값을 빈 문자열로 채우기
df_unique['address'] = df_unique['address'].fillna('')

# '경기'가 포함된 행을 제거
df_filtered = df_unique[~df_unique['address'].str.contains('경기')]

# 필터링된 데이터프레임을 새로운 CSV 파일로 저장
output_path = 'D:/대학교/2024-1학기/심화캡스톤/Crawling/카페 크롤링/카페_filtered.csv'
df_filtered.to_csv(output_path, index=False, encoding='utf-8-sig')

print("필터링된 CSV 파일이 성공적으로 저장되었습니다.")
