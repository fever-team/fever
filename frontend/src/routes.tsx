import React from 'react';
import i18n from './i18n';
import { WithTranslation } from 'react-i18next';
import { RouteProps, RouteComponentProps } from 'react-router-dom';

export interface Props {
  // Add some common props here
}

// New Route Component Interface
interface RouteComponentPropsFixed<T> extends Props, RouteComponentProps { }

// New Props Interface(make component must exist)
interface RoutePropsFixed extends RouteProps, WithTranslation {
  component: React.ComponentType<RouteComponentPropsFixed<any>> | React.ComponentType<any>;
}


// add your components below array
const routes: Array<RoutePropsFixed> = [];

export default routes;
