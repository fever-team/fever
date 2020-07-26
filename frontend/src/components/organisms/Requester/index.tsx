import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import styled from 'styled-components';
import { InputGroup, Button, Tag, Intent } from '@blueprintjs/core';

import { Props as AppProps } from '../../App';
import Form from '../../atoms/Form';
import FlexWrapper from '../../atoms/FlexWrapper';
import MethodSelector, { Method } from '../../molecules/MethodSelector';
import ResponseCard, { ResponseData } from '../../molecules/ResponseCard';

import testOnce, { TestOnceResponse } from '../../../api/controller/testOnce';
import statusIntent from '../../../utils/statusIntent';
import methodState from '../../../utils/requester/method';


export interface Props extends AppProps { }

export interface CardData extends TestOnceResponse {
  intent: Intent;
}


const Wrapper = styled(FlexWrapper)`
  justify-content: space-between;
  align-items: flex-start;

  padding-left: 2rem;
  padding-right: 2rem;
`

const ButtonWrapper = styled.div`
  margin-right: 1rem;
`

const InputGroupWrapper = styled.div`
  flex-grow: 1;
`


const Requester: React.FC<Props> = (props: Props) => {
  const emptyData: ResponseData = {
    status: null,
    duration: null,
    intent: 'none',
  }

  const [userInput, setUserInput] = useState<string>("");
  const [cardLoading, setCardLoading] = useState<boolean | null>(null);
  const [cardData, setCardData] = useState<ResponseData>(emptyData);

  const [method, setMethod] = useRecoilState<Method>(methodState);

  // request에 사용할 url
  const handleChange = (e: React.FormEvent<HTMLInputElement>) => {
    const newValue = e.currentTarget.value;
    setUserInput(newValue);
  }

  // button을 클릭하면 submit
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // request를 보내려는 순간 카드가 나타남. (로딩 중)
    setCardData(emptyData);
    setCardLoading(true);
    const result = await testOnce(userInput, method);

    const cardResult = {
      ...result,
      intent: statusIntent(result.status),
    };
    setCardData(cardResult);
    setCardLoading(false);
  }

  const SubmitButton = <Button
    minimal={true}
    intent="primary"
    icon="arrow-right"
    type="submit"
  />;

  return (
    <Form onSubmit={handleSubmit}>
      <Wrapper>
        <ButtonWrapper>
          <MethodSelector {...props} methodState={methodState} />
        </ButtonWrapper>
        <InputGroupWrapper>
          <InputGroup
            leftIcon='search'
            placeholder={props.i18n.t('input:requester:placeholder')}
            onChange={handleChange}
            rightElement={SubmitButton}
          />
          {cardLoading != null &&
            <ResponseCard loading={cardLoading} data={cardData} {...props} />
          }
        </InputGroupWrapper>
      </Wrapper>
    </Form>
  )
}


export default Requester;