import React from 'react';
import { Tag, Intent } from '@blueprintjs/core';

import Card from '../Card';
import { Props as AppProps } from '../../App'


export interface ResponseData {
  status: null | number;
  duration?: null | number;
  intent: Intent;
}

export interface Props extends AppProps {
  loading: boolean;
  data: ResponseData;
}


const ResponseCard: React.FC<Props> = (props: Props) => {
  return (
    <Card
      loading={props.loading}
      title={props.i18n.t(`input:requester:results:${props.data.intent}`)}
      intent={props.data.intent}
    >
      <p><Tag>status</Tag> { props.data.status }</p>
      <p><Tag>duration</Tag> { props.data.duration }ms</p>
    </Card>
  )
}


export default ResponseCard;