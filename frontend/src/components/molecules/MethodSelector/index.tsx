import React, { useState } from 'react';
import styled from 'styled-components';

import { Button, MenuItem } from "@blueprintjs/core";
import { Select, IItemRendererProps } from "@blueprintjs/select";

import { Props as RequesterProps} from '../../organisms/Requester';


export interface Props extends RequesterProps { }

type Method = 'GET' | 'DELETE' | 'HEAD' | 'OPTIONS' | 'POST' | 'PUT' | 'PATCH';

const MethodSelect = Select.ofType<Method>();


const MethodSelector: React.FC<Props> = (props: Props) => {
  const methods: Method[] = ['GET', 'DELETE', 'HEAD', 'OPTIONS', 'POST', 'PUT', 'PATCH'];
  const [method, setMethod] = useState<Method>(methods[0]);

  const MethodRenderer = (item: Method, itemProps: IItemRendererProps) => {
    return (
      <MenuItem
        key={item}
        text={item}
        onClick={itemProps.handleClick}
      />
    )
  }

  const handleSelect = (item: Method, event?: React.SyntheticEvent<HTMLElement>) => {
    setMethod(item);
  }

  return (
    <MethodSelect
      items={methods}
      itemRenderer={MethodRenderer}
      onItemSelect={handleSelect}
      filterable={false}
    >
      <Button text={method} rightIcon="caret-down" />
    </MethodSelect>
  )
}


export default MethodSelector;