import React from 'react';
import styled from 'styled-components';
import { Card, Text, Elevation, ICardProps } from '@blueprintjs/core';


export interface Props extends ICardProps {
  title: string;
  content: string;
  color: string;
};


const WrappedCard: React.FC<ICardProps> = styled(Card)`
  margin: 2rem;
  padding: 2rem;

  background-color: #CED9E0;
`

const Heading: React.FC<React.HTMLProps<{}>> = styled.h3`
  margin-bottom: 1rem;

  font-weight: bold;
`

const Jumbotron: React.FC<Props> = (props: Props) => {
  return (
    <WrappedCard interactive={false} elevation={Elevation.ONE}>
      <Heading className="bp3-heading">{props.title}</Heading>
      <Text className="bp3-text-large">{props.content}</Text>
    </WrappedCard>
  )
}


export default Jumbotron;