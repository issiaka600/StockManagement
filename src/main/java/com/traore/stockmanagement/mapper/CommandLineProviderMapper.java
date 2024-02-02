package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import com.traore.stockmanagement.model.CommandLineProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineProviderMapper {

    public CommandLineProvider toEntity(CommandLineProviderDTO dto){
        if(dto != null){
            return CommandLineProvider.builder()
                    .unitPrice(dto.getUnitPrice())
                    .quantity(dto.getQuantity())
                    .product(
                            ProductMapper.fromProductDTOToEntity(
                                    dto.getProduct()
                            )
                    )
                    .commandProvider(
                            CommandProviderMapper.toEntityStatic(
                                    dto.getCommandProvider()
                            )
                    )
                    .build();
        }

        return null;
    }
    public CommandLineProviderDTO fromEntity(CommandLineProvider commandLineProvider){
        if(commandLineProvider != null){
            return CommandLineProviderDTO.builder()
                    .id(commandLineProvider.getId())
                    .unitPrice(commandLineProvider.getUnitPrice())
                    .quantity(commandLineProvider.getQuantity())
                    .product(
                            ProductMapper.fromEntityStatic(
                                    commandLineProvider.getProduct()
                            )
                    )
                    .commandProvider(
                            CommandProviderMapper.fromEntityToCommandProviderOnlyDTO(
                                    commandLineProvider.getCommandProvider()
                            )
                    )
                    .build();
        }

        return null;
    }

    public static CommandLineProviderDTO fromEntityStatic(CommandLineProvider commandLineProvider){
        if(commandLineProvider != null){
            return CommandLineProviderDTO.builder()
                    .id(commandLineProvider.getId())
                    .unitPrice(commandLineProvider.getUnitPrice())
                    .quantity(commandLineProvider.getQuantity())
                    .product(
                            ProductMapper.fromEntityStatic(
                                    commandLineProvider.getProduct()
                            )
                    )
                    .commandProvider(
                            CommandProviderMapper.fromEntityToCommandProviderOnlyDTO(
                                    commandLineProvider.getCommandProvider()
                            )
                    )
                    .build();
        }

        return null;
    }

    public static List<CommandLineProviderDTO> fromEntity(List<CommandLineProvider> commandLineProviders){
        if(commandLineProviders != null){
            List<CommandLineProviderDTO> dtos = new ArrayList<>();
            for (CommandLineProvider commandLineProvider : commandLineProviders){
                dtos.add(
                        fromEntityStatic(commandLineProvider)
                );
            }
            return dtos;
        }
        return null;
    }

}
