package tr.com.provera.pameraapi.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tr.com.provera.pameraapi.dao.PrivilegeRepository;
import tr.com.provera.pameraapi.dao.ProjectRepository;
import tr.com.provera.pameraapi.dao.RoleRepository;
import tr.com.provera.pameraapi.dao.UserRepository;
import tr.com.provera.pameraapi.dao.model.Privilege;
import tr.com.provera.pameraapi.dao.model.Role;
import tr.com.provera.pameraapi.dao.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
@Order(1)
public class DataInitCreateUserRole implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {


        Privilege readPrivilege = createPrivilege("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilege("WRITE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilege("DELETE_PRIVILEGE");

        Role adminRole = createRole("ROLE_ADMIN", Arrays.asList(readPrivilege,writePrivilege,deletePrivilege));
        Role memberRole = createRole("ROLE_MEMBER", Arrays.asList(readPrivilege,writePrivilege,deletePrivilege));



        User dbAdminUser = userRepository.findByUsername("admin");
        User dbMemberUser = userRepository.findByUsername("member");
        User dbBothUser = userRepository.findByUsername("both");
        User dbNoneUser = userRepository.findByUsername("noneRole");

        if (dbAdminUser==null) {
            Collection<Role> myroles = new ArrayList<Role>();
            User adminuser = User.builder()
                    .username("admin")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("admin@pamera.xyz")
                    .firstname("Admin")
                    .lastname("User")
                    .phonenum("+1234567890")
                    .roles(Arrays.asList(adminRole))
                    .build();
            userRepository.save(adminuser);
        }

        if (dbMemberUser==null) {
            User memberUser = User.builder()
                    .username("member")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("member@pamera.xyz")
                    .firstname("Member")
                    .lastname("User")
                    .phonenum("+1234567890")
                    .roles(Arrays.asList(memberRole))
                    .build();
            userRepository.save(memberUser);
        }

        if (dbBothUser==null) {
            Collection<Role> myroles = new ArrayList<Role>();
            myroles.add(adminRole);
            myroles.add(memberRole);
            User bothUser = User.builder()
                    .username("both")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("both@pamera.xyz")
                    .firstname("Both")
                    .lastname("User")
                    .phonenum("+1234567890")
                    .roles(myroles)
                    .build();
            userRepository.save(bothUser);
        }

        if (dbNoneUser==null) {
            Collection<Role> myroles = new ArrayList<Role>();
            User noneRoleUser = User.builder()
                    .username("noneRole")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("noneRole@pamera.xyz")
                    .firstname("NoneRole")
                    .lastname("User")
                    .phonenum("+1234567890")
                    .roles(myroles)
                    .build();
            userRepository.save(noneRoleUser);
        }


    /*
        GenericSpecification<Privilege> privilegeGenericSpecification = new GenericSpecification<Privilege>();
        privilegeGenericSpecification.add(new SearchCriteria("name","PRIVILEGE",SearchOperation.MATCH));
        List<Privilege> privilegeList = privilegeRepository.findAll(privilegeGenericSpecification);
        privilegeList.forEach(privilege -> {
            System.out.println(privilege.getName());
        });



        Project projectToSave = Project.builder().name("Project nameExecution").status(ProjectStatus.Execution)
                .build();
        projectRepository.save(projectToSave);
        GenericSpecification projectSpecification = new GenericSpecification<Project>();
        projectSpecification.add(new SearchCriteria("status", ProjectStatus.Execution, SearchOperation.EQUAL));
        //genericSpecification.add(new SearchCriteria("name", "Project nameInitiation",SearchOperation.EQUAL));
        //projectSpecification.add(new SearchCriteria("name", "Project", SearchOperation.MATCH));
        List<Project> projectList = projectRepository.findAll(projectSpecification);
        projectList.forEach(project -> {
            System.out.println(project.getName());
        });

     */
    }

    private Privilege createPrivilege(String name){
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege = Privilege.builder()
                    .name(name)
                    .description(name + " Privilege")
                    .build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    private Role createRole(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.builder()
                    .name(name)
                    .description(name + " role")
                    .build();
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}
