import React from 'react';
import { Props as RouteProps } from '../../../routes';
import { Props as AppProps } from '../../App';

import Header from '@components/Header';


export interface Props extends RouteProps, AppProps { }

const Main: React.FC<Props> = (props: Props) => {
  return (
    <>
      <Header />
      TODO
    </>
  )
}


export default Main;