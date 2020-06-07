import React, { useState } from 'react';
import styled from 'styled-components';

import { sendRequest, SendRequestResponse } from '../../../utils/sendRequest';


export interface Props { }

// TODO(sudosubin): move component to atoms
const Form = styled.form`
`

// TODO(sudosubin): move component to atoms
const Input = styled.input`
  width: 800px;
  margin: 40px;
`

// TODO(sudosubin): move component to atoms
const Button = styled.button`
`


const Requester: React.FC<Props> = (props: Props) => {
  const [userInput, setUserInput] = useState<string>("");
  const [result, setResult] = useState<SendRequestResponse | null>(null);

  const handleChange = (e: React.FormEvent<HTMLInputElement>) => {
    const newValue = e.currentTarget.value;
    setUserInput(newValue);
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const result = await sendRequest(userInput);
    setResult(result);
  }

  return (
    <Form onSubmit={e => handleSubmit(e)}>
      <Input name="url-box" onChange={e => handleChange(e)} />
      <Button type="submit">Send Request</Button>
    </Form>
  )
}


export default Requester;