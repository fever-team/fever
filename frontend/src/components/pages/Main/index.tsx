import React from 'react';

import { Props as RouteProps } from '../../../routes';
import { Props as AppProps } from '../../App';

import PageWrapper from '../../atoms/PageWrapper';
import Jumbotron from '../../organisms/Jumbotron';

import Header from '../../organisms/Header';
import Requester from '../../organisms/Requester';


export interface Props extends RouteProps, AppProps { }

const Main: React.FC<Props> = (props: Props) => {
  return (
    <>
      <Header />
      <PageWrapper>
        <Jumbotron
          title={props.i18n.t('main:jumbotron:titleText')}
          content={props.i18n.t('main:jumbotron:contentText')}
          color={'#15B371'}
        />
        <Requester />
      </PageWrapper>
    </>
  )
}


export default Main;