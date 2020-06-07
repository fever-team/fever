import React from 'react';
import styled from 'styled-components';

export interface Props { }


// TODO(sudosubin): move component to molecules
const Wrapper = styled.div`
  width: 100%;

  margin: 0;
  padding: 10px;

  background-color: #DFDFDF;

  font-size: 16px;
  font-weight: bold;
`


const Header: React.FC<Props> = (props: Props) => {
  return (
    <>
      <Wrapper>Header</Wrapper>
    </>
  )
}


export default Header;