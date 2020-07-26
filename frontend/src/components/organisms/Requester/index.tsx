import React, { useState } from 'react';
import { atom, useRecoilState } from 'recoil';
import styled from 'styled-components';
import { InputGroup, Button, Code } from '@blueprintjs/core';

import Form from '../../atoms/Form';
import FlexWrapper from '../../atoms/FlexWrapper';
import MethodSelector, { Method } from '../../molecules/MethodSelector';
import Card from '../../molecules/Card';
import { Props as MainProps } from '../../pages/Main';

import testOnce, { TestOnceResponse } from '../../../api/controller/testOnce';


export interface Props extends MainProps { }


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


const SubmitButton = <Button minimal={true} intent="primary" icon="arrow-right" />;


const Requester: React.FC<Props> = (props: Props) => {
  const emptyResponse: TestOnceResponse = {
    status: null,
    duration: null,
  }

  const [userInput, setUserInput] = useState<string>("");
  const [cardLoading, setCardLoading] = useState<boolean | null>(null);
  const [cardData, setCardData] = useState<TestOnceResponse>(emptyResponse);

  // request에 사용할 method
  const methodState = atom<Method>({
    key: 'requester-method',
    default: 'GET',
  });
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
    setCardData(emptyResponse);
    setCardLoading(true);
    const result = await testOnce(userInput, method);

    setCardData(result);
    setCardLoading(false);
  }

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
            <Card loading={cardLoading}>
              <div><Code>status</Code> { cardData.status }</div>
              <div><Code>duration</Code> { cardData.duration }</div>
            </Card>
          }
        </InputGroupWrapper>
      </Wrapper>
    </Form>
  )
}


export default Requester;