package br.com.hisig.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;

public class Utils {

  // Copia propriedades não nulas de um objeto de origem para um objeto de destino.
  public static void copyNonNullProperties(@NonNull Object source, @NonNull Object target) {
    try {
      BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    } catch (BeansException e) {
      //Lidar com a exceção de cópia de propriedades, se necessário
      e.printStackTrace();
    }
  }
  
  public static String[] getNullPropertyNames(@NonNull Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);

    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for( PropertyDescriptor pd: pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
