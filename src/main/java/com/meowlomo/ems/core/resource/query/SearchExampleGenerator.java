package com.meowlomo.ems.core.resource.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageRowBounds;
import com.google.common.base.CaseFormat;
import com.meowlomo.ems.config.RuntimeVariables;

@Component
public class SearchExampleGenerator {

    private final Logger logger = LoggerFactory.getLogger(SearchExampleGenerator.class);

    @SuppressWarnings("unchecked")
    public <T, C> T generateExample(UriInfo uriInfo, Object criteria, Class<T> example) {

        // check the type is a class of Example.Criteria
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            // generate the example object and then create the criteria instance
            Object exampleObject = example.newInstance();
            Object criteriaObject = null;
            if (criteria == null) {
                // get the create Criteria method
                Method exampleCriteriaMethod = exampleObject.getClass().getDeclaredMethod("createCriteria");
                criteriaObject = exampleCriteriaMethod.invoke(exampleObject);
            }
            else {
                // initialize the criteria
                criteriaObject = criteria;
            }
            // get the methods
            Map<String, Method> methods = this.getMathods(criteriaObject.getClass().getDeclaredMethods());

            // check the field from the query string map
            String ids = queryParams.getFirst("ids");
            String name = queryParams.getFirst("name");
            String names = queryParams.getFirst("names");
            String comment = queryParams.getFirst("comment");
            String startDateString = queryParams.getFirst("startDate");
            String endDateString = queryParams.getFirst("endDate");
            String orderBy = queryParams.getFirst("orderBy");
            String status = queryParams.getFirst("status");
            String type = queryParams.getFirst("type");
            String runType = queryParams.getFirst("runType");
            String mode = queryParams.getFirst("mode");
            String target = queryParams.getFirst("target");
            String message = queryParams.getFirst("message");
            String values = queryParams.getFirst("values");
            String log = queryParams.getFirst("log");
            String isDriver = queryParams.getFirst("isDriver");
            String isDefault = queryParams.getFirst("isDefault");

            if (ids != null && methods.keySet().contains("andIdIn")) {
                String[] idsStringArray = ids.trim().split(",");
                List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
                        .collect(Collectors.toList());
                Method method = methods.get("andIdIn");
                method.invoke(criteriaObject, idsList);
            }

            if (names != null && methods.keySet().contains("andNameIn")) {
                String[] idsStringArray = names.trim().split(",");
                List<String> namesList = Arrays.asList(idsStringArray);
                Method method = methods.get("andNameIn");
                method.invoke(criteriaObject, namesList);
            }

            if (values != null && methods.keySet().contains("andValueIn")) {
                String[] idsStringArray = values.trim().split(",");
                List<String> valuesList = Arrays.asList(idsStringArray);
                Method method = methods.get("andValueIn");
                method.invoke(criteriaObject, valuesList);
            }

            if (name != null && methods.keySet().contains("andNameLikeInsensitive")) {
                Method method = methods.get("andNameLikeInsensitive");
                method.invoke(criteriaObject, name);
            }

            if (log != null && methods.keySet().contains("andLogLikeInsensitive")) {
                Method method = methods.get("andLogLikeInsensitive");
                method.invoke(criteriaObject, log);
            }

            if (status != null && methods.keySet().contains("andStatusEqualTo")) {
                Method method = methods.get("andStatusEqualTo");
                method.invoke(criteriaObject, status);
            }

            if (type != null && methods.keySet().contains("andTypeEqualTo")) {
                Method method = methods.get("andTypeEqualTo");
                method.invoke(criteriaObject, type);
            }

            if (target != null && methods.keySet().contains("andTargetLikeInsensitive")) {
                Method method = methods.get("andTargetLikeInsensitive");
                method.invoke(criteriaObject, target);
            }

            if (message != null && methods.keySet().contains("andMessageLikeInsensitive")) {
                Method method = methods.get("andMessageLikeInsensitive");
                method.invoke(criteriaObject, message);
            }

            if (comment != null && methods.keySet().contains("andCommentLikeInsensitive")) {
                Method method = methods.get("andCommentLikeInsensitive");
                method.invoke(criteriaObject, comment);
            }

            if ((!queryParams.containsKey("all") && !queryParams.containsKey("ALL") && !queryParams.containsKey("All"))
                    && methods.keySet().contains("andActiveEqualTo")) {
                Method method = methods.get("andActiveEqualTo");
                method.invoke(criteriaObject, true);
            }

            if (runType != null && methods.keySet().contains("andRunTypeEqualTo")) {
                Method method = methods.get("andRunTypeEqualTo");
                method.invoke(criteriaObject, runType);
            }
            else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")
                    && methods.keySet().contains("andRunTypeEqualTo")) {
                Method method = methods.get("andRunTypeEqualTo");
                method.invoke(criteriaObject, mode);
            }

            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            if (startDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
                    && methods.keySet().contains("andCreatedAtGreaterThanOrEqualTo")) {
                startCal.setTimeInMillis(Long.valueOf(startDateString));
                Method method = methods.get("andCreatedAtGreaterThanOrEqualTo");
                method.invoke(criteriaObject, startCal.getTime());
            }

            if (startDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
                    && endDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(endDateString)
                    && methods.keySet().contains("andCreatedAtBetween")) {
                startCal.setTimeInMillis(Long.valueOf(startDateString));
                endCal.setTimeInMillis(Long.valueOf(endDateString));
                Method method = methods.get("andCreatedAtBetween");
                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
            }

            if (endDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(endDateString)
                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
                endCal.setTimeInMillis(Long.valueOf(endDateString));
                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
                method.invoke(criteriaObject, endCal.getTime());
            }

            if (orderBy != null) {
                if (orderBy.indexOf("runDate") != -1) {
                    StringBuilder newOrderBy = new StringBuilder(orderBy);
                    newOrderBy.insert(7, "2");
                    orderBy = newOrderBy.toString();
                }
                Method exampleOrderMethod = exampleObject.getClass().getDeclaredMethod("setOrderByClause",
                        String.class);
                exampleOrderMethod.invoke(exampleObject,
                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy));
            }
            
            // is driver 
            if (isDriver != null && methods.keySet().contains("andIsDriverEqualTo") && BooleanUtils.toBooleanObject(isDriver) != null) {
                Method method = methods.get("andIsDriverEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDriver));
            }
            
            // is default 
            if (isDefault != null && methods.keySet().contains("andIsDefaultEqualTo") && BooleanUtils.toBooleanObject(isDefault) != null) {
                Method method = methods.get("andIsDefaultEqualTo");
                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDefault));
            }
            
//            else {
//                Method exampleOrderMethod = exampleObject.getClass().getDeclaredMethod("setOrderByClause",
//                        String.class);
//                exampleOrderMethod.invoke(exampleObject,
//                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, " id asc "));
//            }

            // set the criteria
            Method exampleOrMethod = exampleObject.getClass().getDeclaredMethod("or", criteriaObject.getClass());
            exampleOrMethod.invoke(exampleObject, criteriaObject);
            return (T) exampleObject;
        }
        catch (InstantiationException e) {
            logger.error("could not create example object instance", e);
        }
        catch (IllegalAccessException e) {
            logger.error("could not create example object instance", e);
        }
        catch (IllegalArgumentException e) {
            logger.error("Error on invoking method", e);
        }
        catch (InvocationTargetException e) {
            logger.error("Error on invoking method", e);
        }
        catch (NoSuchMethodException e) {
            logger.error("Error on creating method object", e);
        }
        catch (SecurityException e) {
            logger.error("Error on creating method object", e);
        }
        return null;
    }

    private Map<String, Method> getMathods(Method[] methodList) {
        // loop through the method
        Map<String, Method> methods = new HashMap<String, Method>();
        for (int i = 0; i < methodList.length; i++) {
            Method method = methodList[i];
            methods.put(method.getName(), method);
        }
        return methods;
    }

    public RowBounds generateSearchRowBounds(UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        // check id default, no page size
        if (queryParams.getFirst("pageSize") == null) {
            return new PageRowBounds(0, RuntimeVariables.DEFAULT_LIMIT);
        }
        // check not default , and all
        else if (queryParams.getFirst("pageSize") != null && (queryParams.getFirst("pageSize").equalsIgnoreCase("all")
                || queryParams.getFirst("pageSize").equalsIgnoreCase("ALL")
                || queryParams.getFirst("pageSize").equalsIgnoreCase("All"))) {
            return new RowBounds();
        }
        // check not default , and all
        else {
            String pageSize = queryParams.getFirst("pageSize");
            String pageNumber = queryParams.getFirst("pageNumber");
            if (pageSize != null && pageNumber != null && org.apache.commons.lang3.StringUtils.isNumeric(pageSize)
                    && org.apache.commons.lang3.StringUtils.isNumeric(pageNumber)) {

                int limit = Integer.parseInt(pageSize);
                int offect = limit * (Integer.parseInt(pageNumber) - 1);
                return new PageRowBounds(offect, limit);
            }
            else {
                return new PageRowBounds(0, RuntimeVariables.DEFAULT_LIMIT);
            }
        }
    }

    // public ApplicationExample generateSearchApplicationExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ApplicationExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ApplicationExample internalExample = new ApplicationExample();
    // com.meowlomo.atm.model.ApplicationExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // ApplicationExample example = new ApplicationExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ElementActionExample generateSearchElementActionExample(UriInfo
    // uriInfo,
    // com.meowlomo.atm.model.ElementActionExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ElementActionExample internalExample = new ElementActionExample();
    // com.meowlomo.atm.model.ElementActionExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // ElementActionExample example = new ElementActionExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ElementLocatorTypeExample
    // generateSearchElementLocatorTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ElementLocatorTypeExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ElementLocatorTypeExample internalExample = new ElementLocatorTypeExample();
    // com.meowlomo.atm.model.ElementLocatorTypeExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // ElementLocatorTypeExample example = new ElementLocatorTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public DriverExample generateSearchDriverExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.DriverExample.Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // DriverExample internalExample = new DriverExample();
    // com.meowlomo.atm.model.DriverExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // DriverExample example = new DriverExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public DriverEntryExample generateSearchDriverEntryExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.DriverEntryExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // DriverEntryExample internalExample = new DriverEntryExample();
    // com.meowlomo.atm.model.DriverEntryExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // DriverEntryExample example = new DriverEntryExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ElementExample generateSearchElementExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ElementExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ElementExample internalExample = new ElementExample();
    // com.meowlomo.atm.model.ElementExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // ElementExample example = new ElementExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ElementTypeExample generateSearchElementTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ElementTypeExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ElementTypeExample internalExample = new ElementTypeExample();
    // com.meowlomo.atm.model.ElementTypeExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // ElementTypeExample example = new ElementTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public SystemRequirementExample
    // generateSearchSystemRequirementExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.SystemRequirementExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // SystemRequirementExample internalExample = new SystemRequirementExample();
    // com.meowlomo.atm.model.SystemRequirementExample.Criteria criteria
    // = null;
    // if (externalCriteria == null) {
    // SystemRequirementExample example = new SystemRequirementExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public SystemRequirementEntryExample
    // generateSearchSystemRequirementEntryExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.SystemRequirementEntryExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // SystemRequirementEntryExample internalExample = new
    // SystemRequirementEntryExample();
    // com.meowlomo.atm.model.SystemRequirementEntryExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // SystemRequirementEntryExample example = new SystemRequirementEntryExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public FileExample generateSearchFileExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.FileExample.Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // FileExample internalExample = new FileExample();
    // com.meowlomo.atm.model.FileExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // FileExample example = new FileExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String name = queryParams.getFirst("name");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    // String runType = queryParams.getFirst("runType");
    // String mode = queryParams.getFirst("mode");
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (runType != null) {
    // criteria.andRunTypeEqualTo(runType);
    // }
    // else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
    // criteria.andRunTypeEqualTo("DEVELOPMENT");
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public GroupExample generateSearchGroupExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.GroupExample.Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // GroupExample internalExample = new GroupExample();
    // com.meowlomo.atm.model.GroupExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // GroupExample example = new GroupExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public InstructionExample generateSearchInstructionExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.InstructionExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // InstructionExample internalExample = new InstructionExample();
    // com.meowlomo.atm.model.InstructionExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // InstructionExample example = new InstructionExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String target = queryParams.getFirst("target");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (target != null) {
    // criteria.andTargetLikeInsensitive(target);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // else {
    // internalExample.setOrderByClause(" order_index asc ");
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public InstructionResultExample
    // generateSearchInstructionResultExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.InstructionResultExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // InstructionResultExample internalExample = new InstructionResultExample();
    // com.meowlomo.atm.model.InstructionResultExample.Criteria criteria
    // = null;
    // if (externalCriteria == null) {
    // InstructionResultExample example = new InstructionResultExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String target = queryParams.getFirst("target");
    // String action = queryParams.getFirst("action");
    // String orderBy = queryParams.getFirst("orderBy");
    // String runType = queryParams.getFirst("runType");
    // String mode = queryParams.getFirst("mode");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (target != null) {
    // criteria.andTargetLikeInsensitive(target);
    // }
    //
    // if (action != null) {
    // criteria.andActionLikeInsensitive(action);
    // }
    //
    // if (runType != null) {
    // criteria.andRunTypeEqualTo(runType);
    // }
    // else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
    // criteria.andRunTypeEqualTo("DEVELOPMENT");
    // }
    //
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public DriverPropertyExample generateSearchDriverPropertyExample(UriInfo
    // uriInfo,
    // com.meowlomo.atm.model.DriverPropertyExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // DriverPropertyExample internalExample = new DriverPropertyExample();
    // com.meowlomo.atm.model.DriverPropertyExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // DriverPropertyExample example = new DriverPropertyExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public FileTypeExample generateSearchFileTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.FileTypeExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // FileTypeExample internalExample = new FileTypeExample();
    // com.meowlomo.atm.model.FileTypeExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // FileTypeExample example = new FileTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ProjectTypeExample generateSearchProjectTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ProjectTypeExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ProjectTypeExample internalExample = new ProjectTypeExample();
    // com.meowlomo.atm.model.ProjectTypeExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // ProjectTypeExample example = new ProjectTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public TestCaseFolderTypeExample
    // generateSearchTestCaseFolderTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.TestCaseFolderTypeExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // TestCaseFolderTypeExample internalExample = new TestCaseFolderTypeExample();
    // com.meowlomo.atm.model.TestCaseFolderTypeExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // TestCaseFolderTypeExample example = new TestCaseFolderTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ProjectExample generateSearchProjectExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ProjectExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ProjectExample internalExample = new ProjectExample();
    // com.meowlomo.atm.model.ProjectExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // ProjectExample example = new ProjectExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public PropertyExample generateSearchPropertyExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.PropertyExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // PropertyExample internalExample = new PropertyExample();
    // com.meowlomo.atm.model.PropertyExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // PropertyExample example = new PropertyExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public RunExample generateSearchRunExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.RunExample.Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // RunExample internalExample = new RunExample();
    // com.meowlomo.atm.model.RunExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // RunExample example = new RunExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    // String type = queryParams.getFirst("type");
    // String mode = queryParams.getFirst("mode");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (type != null) {
    // criteria.andTypeEqualTo(type);
    // }
    // else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
    // criteria.andTypeEqualTo("DEVELOPMENT");
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public RunSetExample generateSearchRunSetExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.RunSetExample.Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // RunSetExample internalExample = new RunSetExample();
    // com.meowlomo.atm.model.RunSetExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // RunSetExample example = new RunSetExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public SectionLineExample generateSearchSectionLineExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.SectionLineExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // SectionLineExample internalExample = new SectionLineExample();
    // com.meowlomo.atm.model.SectionLineExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // SectionLineExample example = new SectionLineExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public SectionExample generateSearchSectionExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.SectionExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // SectionExample internalExample = new SectionExample();
    // com.meowlomo.atm.model.SectionExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // SectionExample example = new SectionExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public StepLogExample generateSearchStepLogExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.StepLogExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // StepLogExample internalExample = new StepLogExample();
    // com.meowlomo.atm.model.StepLogExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // StepLogExample example = new StepLogExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    // String message = queryParams.getFirst("message");
    // String runType = queryParams.getFirst("runType");
    // String mode = queryParams.getFirst("mode");
    //
    // if (message != null) {
    // criteria.andMessageLikeInsensitive(message);
    // }
    //
    // if (runType != null) {
    // criteria.andRunTypeEqualTo(runType);
    // }
    // else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
    // criteria.andRunTypeEqualTo("DEVELOPMENT");
    // }
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public InstructionOptionExample
    // generateSearchInstructionOptionExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.InstructionOptionExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // InstructionOptionExample internalExample = new InstructionOptionExample();
    // com.meowlomo.atm.model.InstructionOptionExample.Criteria criteria
    // = null;
    // if (externalCriteria == null) {
    // InstructionOptionExample example = new InstructionOptionExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public InstructionOptionEntryExample
    // generateSearchInstructionOptionEntryExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.InstructionOptionEntryExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // InstructionOptionEntryExample internalExample = new
    // InstructionOptionEntryExample();
    // com.meowlomo.atm.model.InstructionOptionEntryExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // InstructionOptionEntryExample example = new InstructionOptionEntryExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String names = queryParams.getFirst("names");
    // String values = queryParams.getFirst("values");
    // String ids = queryParams.getFirst("ids");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] namesStringArray = names.trim().split(",");
    // List<String> namesList = Arrays.asList(namesStringArray);
    // criteria.andNameIn(namesList);
    // }
    //
    // if (values != null) {
    // String[] valuesStringArray = values.trim().split(",");
    // List<String> valuesList = Arrays.asList(valuesStringArray);
    // criteria.andNameIn(valuesList);
    // }
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public StorageExample generateSearchStorageExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.StorageExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // StorageExample internalExample = new StorageExample();
    // com.meowlomo.atm.model.StorageExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // StorageExample example = new StorageExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public StorageEntryExample generateSearchStorageEntryExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.StorageEntryExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // StorageEntryExample internalExample = new StorageEntryExample();
    // com.meowlomo.atm.model.StorageEntryExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // StorageEntryExample example = new StorageEntryExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public TestCaseFolderExample generateSearchTestCaseFolderExample(UriInfo
    // uriInfo,
    // com.meowlomo.atm.model.TestCaseFolderExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // TestCaseFolderExample internalExample = new TestCaseFolderExample();
    // com.meowlomo.atm.model.TestCaseFolderExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // TestCaseFolderExample example = new TestCaseFolderExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public TestCaseOptionExample generateSearchTestCaseOptionExample(UriInfo
    // uriInfo,
    // com.meowlomo.atm.model.TestCaseOptionExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // TestCaseOptionExample internalExample = new TestCaseOptionExample();
    // com.meowlomo.atm.model.TestCaseOptionExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // TestCaseOptionExample example = new TestCaseOptionExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public TestCaseOptionEntryExample
    // generateSearchTestCaseOptionEntryExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.TestCaseOptionEntryExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // TestCaseOptionEntryExample internalExample = new
    // TestCaseOptionEntryExample();
    // com.meowlomo.atm.model.TestCaseOptionEntryExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // TestCaseOptionEntryExample example = new TestCaseOptionEntryExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public TestCaseExample generateSearchTestCaseExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.TestCaseExample.Criteria externalCriteria)
    // {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // TestCaseExample internalExample = new TestCaseExample();
    // com.meowlomo.atm.model.TestCaseExample.Criteria criteria = null;
    // if (externalCriteria == null) {
    // TestCaseExample example = new TestCaseExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String comment = queryParams.getFirst("comment");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // if (comment != null) {
    // criteria.andCommentLikeInsensitive(comment);
    // }
    //
    // if (!queryParams.containsKey("all") && !queryParams.containsKey("ALL") &&
    // !queryParams.containsKey("All")) {
    // criteria.andActiveEqualTo(true);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ResultReportTestCaseExample
    // generateSearchResultReportTestCaseExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ResultReportTestCaseExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ResultReportTestCaseExample internalExample = new
    // ResultReportTestCaseExample();
    // com.meowlomo.atm.model.ResultReportTestCaseExample.Criteria
    // criteria = null;
    // if (externalCriteria == null) {
    // ResultReportTestCaseExample example = new ResultReportTestCaseExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    // String ids = queryParams.getFirst("ids");
    // String name = queryParams.getFirst("name");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // if (name != null) {
    // criteria.andNameLikeInsensitive(name);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andRunDateBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public InstructionTypeExample generateSearchInstructionTypeExample(UriInfo
    // uriInfo, Criteria externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // InstructionTypeExample internalExample = new InstructionTypeExample();
    // com.meowlomo.atm.model.InstructionTypeExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // InstructionTypeExample example = new InstructionTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (name != null) {
    // criteria.andNameLike(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public StepLogTypeExample generateSearchStepLogTypeExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.StepLogTypeExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // StepLogTypeExample internalExample = new StepLogTypeExample();
    // com.meowlomo.atm.model.StepLogTypeExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // StepLogTypeExample example = new StepLogTypeExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    // String name = queryParams.getFirst("name");
    // String orderBy = queryParams.getFirst("orderBy");
    //
    // if (name != null) {
    // criteria.andNameLike(name);
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
    //
    // public ExecutionLogExample generateSearchExecutionLogExample(UriInfo uriInfo,
    // com.meowlomo.atm.model.ExecutionLogExample.Criteria
    // externalCriteria) {
    // MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    // ExecutionLogExample internalExample = new ExecutionLogExample();
    // com.meowlomo.atm.model.ExecutionLogExample.Criteria criteria =
    // null;
    // if (externalCriteria == null) {
    // ExecutionLogExample example = new ExecutionLogExample();
    // criteria = example.createCriteria();
    // }
    // else {
    // criteria = externalCriteria;
    // }
    //
    // String ids = queryParams.getFirst("ids");
    // String startDateString = queryParams.getFirst("startDate");
    // String endDateString = queryParams.getFirst("endDate");
    // String orderBy = queryParams.getFirst("orderBy");
    // String message = queryParams.getFirst("message");
    // String runType = queryParams.getFirst("runType");
    // String mode = queryParams.getFirst("mode");
    //
    // if (message != null) {
    // criteria.andMessageLikeInsensitive(message);
    // }
    //
    // if (runType != null) {
    // criteria.andRunTypeEqualTo(runType);
    // }
    // else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")) {
    // criteria.andRunTypeEqualTo("DEVELOPMENT");
    // }
    //
    // if (ids != null) {
    // String[] idsStringArray = ids.trim().split(",");
    // List<Long> idsList =
    // Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
    // .collect(Collectors.toList());
    // criteria.andIdIn(idsList);
    // }
    //
    // Calendar startCal = Calendar.getInstance();
    // Calendar endCal = Calendar.getInstance();
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // criteria.andCreatedAtGreaterThanOrEqualTo(startCal.getTime());
    // }
    //
    // if (startDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
    // && endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // startCal.setTimeInMillis(Long.valueOf(startDateString));
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtBetween(startCal.getTime(), endCal.getTime());
    // }
    //
    // if (endDateString != null &&
    // org.apache.commons.lang3.StringUtils.isNumeric(endDateString)) {
    // endCal.setTimeInMillis(Long.valueOf(endDateString));
    // criteria.andCreatedAtLessThanOrEqualTo(endCal.getTime());
    // }
    //
    // if (orderBy != null) {
    // internalExample.setOrderByClause(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
    // orderBy));
    // }
    // internalExample.or(criteria);
    // return internalExample;
    // }
}
