import React from 'react';
import styled from 'styled-components';
import { Card as BPCard, Elevation, Spinner } from '@blueprintjs/core';

export interface Props {
  children: React.ReactNode;
  loading: boolean;
}


const BluePrintCard = styled(BPCard)`
  margin-top: 1rem;
  margin-bottom: 1rem;
`


const Card: React.FC<Props> = (props: Props) => {
  const getContent = (props: Props) => {
    if (props.loading) {
      return <Spinner intent="none" size={30} />;
    }
    return props.children;
  }

  // 로딩 중일 때에는 Spinner를 render함.
  const content = getContent(props);

  return (
    <BluePrintCard interactive={false} elevation={Elevation.ZERO}>
      {content}
    </BluePrintCard>
  )
}

export default Card;