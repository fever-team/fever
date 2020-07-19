import React, { useState } from 'react';
import { atom, useRecoilState } from 'recoil';
import styled from 'styled-components';
import { InputGroup, Button } from '@blueprintjs/core';

import Form from '../../atoms/Form';
import MethodSelector, { Method } from '../../molecules/MethodSelector';
import { Props as MainProps } from '../../pages/Main';

import testOnce from '../../../api/controller/testOnce';


export interface Props extends MainProps { }


const SubmitButton = <Button minimal={true} intent="primary" icon="arrow-right" />;


const Requester: React.FC<Props> = (props: Props) => {
  const [userInput, setUserInput] = useState<string>("");

  // request에 사용할 method
  const methodState = atom<Method>({
    key: 'method',
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
    const result = await testOnce(userInput, 'get');
    console.log(result);
  }

  return (
    <Form onSubmit={handleSubmit}>
      <MethodSelector {...props} methodState={methodState} />
      <InputGroup
        leftIcon="search"
        placeholder={props.i18n.t('input:requester:placeholder')}
        onChange={handleChange}
        large={true}
        rightElement={SubmitButton}
      />
    </Form>
  )
}


export default Requester;