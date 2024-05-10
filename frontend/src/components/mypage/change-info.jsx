import React from 'react';

import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';

import { useUser } from '../../state/user-context';
import IdInput from '../../components/input/id-input';
import PasswordInput from '../../components/input/password-input';
import PasswordConfirmInput from '../../components/input/password-confirm-input';
import NameInput from '../../components/input/name-input';
import DateInput from '../../components/input/date-input';
import Option from '../../components/input/option';
import SubmitButton from '../../components/button/submit-button';

function ChangeInfoPage() {
  const { currentUser, setUserInfo } = useUser();
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
    control,
    getValues,
  } = useForm({ mode: 'onBlur' });

  const onSubmit = (data) => {
    const newData = { ...currentUser, ...data };
    const newId = data.id;

    setUserInfo(newData, newId);

    alert('회원정보가 성공적으로 변경되었습니다.');
    navigate('/mypage');
  };

  return (
    <div className="relative z-10 flex w-full animate-slidein flex-col items-center justify-center">
      <div className="my-10 flex h-[500px] w-full flex-col items-center justify-start overflow-y-auto rounded-xl bg-white py-4 shadow-lg">
        <h2 className="my-5 ml-5 text-right text-3xl font-semibold">
          내 정보 변경하기
        </h2>
        <form
          className="w-full table-fixed p-6"
          onSubmit={handleSubmit(onSubmit)}
        >
          {currentUser && (
            <>
              <div className="rounded-xl border p-4 shadow-lg">
                <IdInput
                  register={register}
                  errors={errors}
                  defaultValue={currentUser.id}
                  disabled
                />
                <NameInput
                  register={register}
                  errors={errors}
                  defaultValue={currentUser.name}
                  disabled
                />
                <PasswordInput
                  register={register}
                  errors={errors}
                  defaultValue={currentUser.password}
                />
                <PasswordConfirmInput
                  register={register}
                  errors={errors}
                  getValues={getValues}
                />
                <DateInput
                  id="birth"
                  label="Birth"
                  placeholder="생년월일을 입력해주세요."
                  control={control}
                  defaultValue={new Date(currentUser.birth)}
                />
                <Option
                  label="MBTI"
                  id="mbti"
                  register={register}
                  defaultValue={currentUser.mbti}
                >
                  <option value="ISTJ">ISTJ</option>
                  <option value="ISFJ">ISFJ</option>
                  <option value="INFJ">INFJ</option>
                  <option value="INTJ">INTJ</option>
                  <option value="ISTP">ISTP</option>
                  <option value="ISFP">ISFP</option>
                  <option value="INFP">INFP</option>
                  <option value="INTP">INTP</option>
                  <option value="ESTP">ESTP</option>
                  <option value="ESFP">ESFP</option>
                  <option value="ENFP">ENFP</option>
                  <option value="ENTP">ENTP</option>
                  <option value="ESTJ">ESTJ</option>
                  <option value="ESFJ">ESFJ</option>
                  <option value="ENFJ">ENFJ</option>
                  <option value="ENTJ">ENTJ</option>
                </Option>
                <Option
                  label="Gender"
                  id="gender"
                  register={register}
                  defaultValue={currentUser.gender}
                >
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                </Option>
              </div>
              <div className="my-3 flex justify-center border-b py-3">
                <SubmitButton text="정보 변경" />
              </div>
            </>
          )}
        </form>
      </div>
    </div>
  );
}

export default ChangeInfoPage;
