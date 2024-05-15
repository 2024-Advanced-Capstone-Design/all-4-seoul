import React, { useState, useEffect } from 'react';
import { getUserInfo } from '../../lib/get-user-info'; // Importing getUserInfo function

function MyInfoPage() {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    async function fetchUserInfo() {
      const userInfo = await getUserInfo(userId);
      setCurrentUser(userInfo);
    }
    fetchUserInfo();
  }, []);

  const birthDate = currentUser ? new Date(currentUser.birth) : null;

  const formatDate = (date) => {
    if (!date) return '';
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}년 ${month}월 ${day}일`;
  };

  return (
    <div className="relative z-10 flex w-full animate-slidein flex-col items-center justify-center">
      <div className="my-10 flex h-auto w-full flex-col items-center justify-center rounded-xl bg-white p-4 shadow-lg">
        {currentUser ? (
          <div className="w-full rounded-xl p-4 shadow-lg">
            <h3 className="text-left font-gmarketbold text-xl">기본정보</h3>
            <ul className="text-left">
              <li className="flex items-center border-gray-200 py-2">
                <img
                  className="mr-4 rounded-full"
                  src="https://phinf.pstatic.net/contact/20191213_151/1576231006104cC7Kd_JPEG/KakaoTalk_20191105_193719253.jpg?type=s160"
                  width="56"
                  height="56"
                  alt="내 프로필 이미지"
                />
                <div className="flex flex-col">
                  <div className="font-bold">{currentUser.userName}</div>
                  <div className="text-sm text-gray-500">
                    {currentUser.loginId}
                  </div>
                </div>
              </li>
              <li className="flex items-center border-t border-gray-200 py-4">
                <span className="mx-4">이름 :</span>
                <span>{currentUser.userName}</span>
              </li>
              <li className="flex items-center border-t border-gray-200 py-4">
                <span className="mx-4">생일 :</span>
                <span>{formatDate(birthDate)}</span>
              </li>
              <li className="flex items-center border-t border-gray-200 py-4">
                <span className="mx-4">MBTI :</span>
                <span>{currentUser.mbti}</span>
              </li>
              <li className="flex items-center border-t border-gray-200 py-4">
                <span className="mx-4">성별 :</span>
                <span>{currentUser.gender}</span>
              </li>
              <li className="flex items-center border-t border-gray-200 py-4">
                <span className="mx-4">별명 :</span>
                <span>{currentUser.nickname}</span>
              </li>
            </ul>
          </div>
        ) : (
          <p className="text-lg">로그인이 필요합니다.</p>
        )}
      </div>
    </div>
  );
}

export default MyInfoPage;
