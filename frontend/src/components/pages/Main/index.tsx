import React from 'react';

import { Props as RouteProps } from '../../../routes';
import { Props as AppProps } from '../../App';

import Header from '../../organisms/Header';
import Requester from '../../organisms/Requester';


export interface Props extends RouteProps, AppProps { }

const Main: React.FC<Props> = (props: Props) => {
  return (
    <>
      <Header />
      <Requester />
    </>
  )
}


export default Main;