package io.choerodon.asgard.infra.feign;


import java.util.List;

import org.hzero.common.HZeroService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.asgard.api.vo.*;
import io.choerodon.asgard.infra.feign.fallback.IamFeignClientFallback;



/**
 * @author dengyouquan
 **/
@FeignClient(value = HZeroService.Iam.NAME, path = "/choerodon/v1", fallback = IamFeignClientFallback.class)
public interface IamFeignClient {
    @GetMapping(value = "/organizations/{organization_id}")
    ResponseEntity<Organization> queryOrganization(@PathVariable(name = "organization_id") Long id);

    @GetMapping(value = "/projects/{project_id}")
    ResponseEntity<Project> queryProject(@PathVariable(name = "project_id") Long id);

    @PostMapping(value = "/site/role_members/users")
    ResponseEntity<List<UserDTO>> pagingQueryUsersByRoleIdOnSiteLevel(@RequestParam(value = "role_id") Long roleId);

    @PostMapping(value = "/organizations/{organization_id}/role_members/users")
    ResponseEntity<List<UserDTO>> pagingQueryUsersByRoleIdOnOrganizationLevel(
            @RequestParam(value = "role_id") Long roleId,
            @PathVariable(name = "organization_id") Long sourceId);

    @PostMapping(value = "/projects/{project_id}/role_members/users")
    ResponseEntity<List<UserDTO>> pagingQueryUsersByRoleIdOnProjectLevel(
            @RequestParam(value = "role_id") Long roleId,
            @PathVariable(name = "project_id") Long sourceId);

    @GetMapping(value = "/roles")
    ResponseEntity<Role> queryByCode(@RequestParam(value = "code") String code);

    @PostMapping("/users/ids")
    ResponseEntity<List<User>> listUsersByIds(@RequestBody Long[] ids);

    @PostMapping("/users/{id}/info")
    ResponseEntity<User> queryInfo(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/users/registrant")
    ResponseEntity<RegistrantInfo> queryRegistrantAndAdminId(@RequestParam(value = "org_code") String orgCode);

    @GetMapping(value = "/organizations/{organization_id}/projects/all")
    ResponseEntity<List<ProjectVO>> listProjectsByOrgId(@PathVariable(name = "organization_id") Long organizationId);
}
