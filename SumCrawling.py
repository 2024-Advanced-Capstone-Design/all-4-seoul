import pandas as pd
import os

# CSV 파일들이 저장된 디렉토리 경로
directory_path = 'D:/대학교/2024-1학기/심화캡스톤/Crawling/맛집 크롤링/'

# 모든 CSV 파일 이름을 가져오기
all_files = [f for f in os.listdir(directory_path) if f.endswith('.csv')]

# 빈 데이터프레임 리스트 초기화
df_list = []

# 각 CSV 파일을 읽어서 데이터프레임 리스트에 추가
for file in all_files:
    file_path = os.path.join(directory_path, file)
    df = pd.read_csv(file_path)
    df_list.append(df)

# 모든 데이터프레임을 하나로 합치기
combined_df = pd.concat(df_list, ignore_index=True)

# 합쳐진 데이터프레임을 새로운 CSV 파일로 저장
combined_df.to_csv('D:/대학교/2024-1학기/심화캡스톤/Crawling/맛집 크롤링/맛집.csv', index=False, encoding='utf-8-sig')

print("CSV 파일들이 성공적으로 합쳐졌습니다.")
