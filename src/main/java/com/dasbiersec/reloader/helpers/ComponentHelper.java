package com.dasbiersec.reloader.helpers;

import com.dasbiersec.reloader.dto.ComponentDTO;
import com.dasbiersec.reloader.model.Component;
import org.springframework.stereotype.Service;

@Service
public class ComponentHelper
{
    public void copyPropsFromDTO(Component component, ComponentDTO componentDTO)
    {
        // copy new details
        component.setId(componentDTO.getId());
        component.setAmount(componentDTO.getAmount());
        component.setCost(componentDTO.getCost());
        component.setName(componentDTO.getName());
        component.setType(componentDTO.getType());
    }
}
