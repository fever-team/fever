import React from 'react';
import { atom, useRecoilState, RecoilState } from 'recoil';
import styled from 'styled-components';

import { Button, MenuItem } from "@blueprintjs/core";
import { Select, IItemRendererProps } from "@blueprintjs/select";

import { Props as RequesterProps} from '../../organisms/Requester';


export interface Props extends RequesterProps {
  methodState: RecoilState<Method>;
}

export type Method = 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE' | 'HEAD' | 'OPTIONS';

const MethodSelect = Select.ofType<Method>();


const MethodSelector: React.FC<Props> = (props: Props) => {
  const methods: Method[] = ['GET', 'POST', 'PUT', 'PATCH', 'DELETE', 'HEAD', 'OPTIONS'];
  const [method, setMethod] = useRecoilState<Method>(props.methodState);

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