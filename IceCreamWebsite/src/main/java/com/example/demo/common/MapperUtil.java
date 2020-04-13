package com.example.demo.common;

import com.example.demo.dto.LoginResDTO;
import com.example.demo.dto.RecipeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.models.Recipe;
public class MapperUtil {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D, T> Page<D> mapPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));

    }

    public static <D, T> D mapObject(final T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }


    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public static RecipeDTO recipeToDTO(Recipe recipe) {
        RecipeDTO recipeDTO;
        recipeDTO = new RecipeDTO(recipe.getId(), recipe.getUser().getUserId(), recipe.getIcecream().getId(),
                recipe.getTitle(), recipe.getDescription(), recipe.getPrice(), recipe.getStatus(),
        recipe.getViewCount(), recipe.getImage(), recipe.getDetails(), recipe.getUploadDate());
        return recipeDTO;
    }
    public static LoginResDTO loginResponseDTO(LoginResDTO LoginResDTO) {
        return new LoginResDTO(
                LoginResDTO.getUserName(),
                LoginResDTO.getToken()
        );
    }
}
