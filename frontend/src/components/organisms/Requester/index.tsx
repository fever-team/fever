import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import styled from 'styled-components';
import { InputGroup, Button, Tag, Intent } from '@blueprintjs/core';

import Form from '../../atoms/Form';
import FlexWrapper from '../../atoms/FlexWrapper';
import MethodSelector, { Method } from '../../molecules/MethodSelector';
import Card from '../../molecules/Card';
import { Props as MainProps } from '../../pages/Main';

import testOnce, { TestOnceResponse } from '../../../api/controller/testOnce';
import statusIntent from '../../../utils/statusIntent';
import methodState from '../../../utils/requester/method';


export interface Props extends MainProps { }

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


const SubmitButton = <Button minimal={true} intent="primary" icon="arrow-right" />;


const Requester: React.FC<Props> = (props: Props) => {
  const emptyResponse: CardData = {
    status: null,
    duration: null,
    intent: 'none',
  }

  const [userInput, setUserInput] = useState<string>("");
  const [cardLoading, setCardLoading] = useState<boolean | null>(null);
  const [cardData, setCardData] = useState<CardData>(emptyResponse);

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

    const cardResult = {
      ...result,
      intent: statusIntent(result.status),
    };
    setCardData(cardResult);
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
            <Card
              loading={cardLoading}
              title={props.i18n.t(`input:requester:results:${cardData.intent}`)}
              intent={cardData.intent}
            >
              <p><Tag>status</Tag> { cardData.status }</p>
              <p><Tag>duration</Tag> { cardData.duration }</p>
            </Card>
          }
        </InputGroupWrapper>
      </Wrapper>
    </Form>
  )
}


export default Requester;