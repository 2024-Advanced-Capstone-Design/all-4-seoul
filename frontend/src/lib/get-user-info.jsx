import axios from 'axios';

export async function getUserInfo() {
  try {
    const response = await axios.get('http://localhost:8080/user-info', {
      withCredentials: true // 쿠키를 자동으로 포함하여 요청합니다.
    });
    return response.data;
  } catch (error) {
    console.error('사용자 정보를 가져오는 데 실패했습니다.', error);
    return null;
  }
}
