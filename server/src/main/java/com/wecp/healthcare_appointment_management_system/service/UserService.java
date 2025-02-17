

package com.wecp.healthcare_appointment_management_system.service;

import com.wecp.healthcare_appointment_management_system.entity.Doctor;
import com.wecp.healthcare_appointment_management_system.entity.Patient;
import com.wecp.healthcare_appointment_management_system.entity.Receptionist;
import com.wecp.healthcare_appointment_management_system.entity.User;
import com.wecp.healthcare_appointment_management_system.repository.DoctorRepository;
import com.wecp.healthcare_appointment_management_system.repository.PatientRepository;
import com.wecp.healthcare_appointment_management_system.repository.ReceptionistRepository;
import com.wecp.healthcare_appointment_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Doctor registerDoctor(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    public Patient registerPatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientRepository.save(patient);
    }
    public Receptionist registerReceptionist(Receptionist receptionist) {
        receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        return receptionistRepository.save(receptionist);
    }

    public User registerUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    
public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        // Update the user details
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        // Save the updated user
        return userRepository.save(existingUser);
    }
}











// package com.wecp.healthcare_appointment_management_system.service;

// import com.wecp.healthcare_appointment_management_system.entity.Doctor;
// import com.wecp.healthcare_appointment_management_system.entity.Patient;
// import com.wecp.healthcare_appointment_management_system.entity.Receptionist;
// import com.wecp.healthcare_appointment_management_system.entity.User;
// import com.wecp.healthcare_appointment_management_system.repository.DoctorRepository;
// import com.wecp.healthcare_appointment_management_system.repository.PatientRepository;
// import com.wecp.healthcare_appointment_management_system.repository.ReceptionistRepository;
// import com.wecp.healthcare_appointment_management_system.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import java.util.Collections;

// @Service
// public class UserService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;
//     @Autowired
//     private DoctorRepository doctorRepository;
//     @Autowired
//     private PatientRepository patientRepository;
//     @Autowired
//     private ReceptionistRepository receptionistRepository;
//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     public Doctor registerDoctor(Doctor doctor) {
//         doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
//         return doctorRepository.save(doctor);
//     }

//     public Patient registerPatient(Patient patient) {
//         patient.setPassword(passwordEncoder.encode(patient.getPassword()));
//         return patientRepository.save(patient);
//     }

//     public Receptionist registerReceptionist(Receptionist receptionist) {
//         receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
//         return receptionistRepository.save(receptionist);
//     }

//     public User getUserByUsername(String username) {
//         return userRepository.findByUsername(username).orElseThrow();
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = getUserByUsername(username);
//         return new org.springframework.security.core.userdetails.User(
//             user.getUsername(),
//             user.getPassword(),
//             Collections.singleton(() -> user.getRole())
//         );
//     }
// }
































































// // @Service
// // public class UserService implements UserDetailsService{

// //     @Autowired
// //     private UserRepository userRepository;

// //     @Autowired
// //     private PasswordEncoder passwordEncoder;

// //     public User registerUser(User user) {
// //         user.setPassword(passwordEncoder.encode(user.getPassword()));
// //         return userRepository.save(user);
// //     }

// //     public User getUserByUsername(String username) {
// //         return userRepository.findByUsername(username).orElseThrow();
// //     }

// //     @Override
// //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// //         User user = getUserByUsername(username);
// //         return new org.springframework.security.core.userdetails.User(
// //             user.getUsername(),
// //             user.getPassword(),
// //             Collections.singleton(() -> user.getRole())
// //         );
// //     }

// // }



// // package com.wecp.healthcare_appointment_management_system.service;

// // import com.wecp.healthcare_appointment_management_system.entity.User;
// // import com.wecp.healthcare_appointment_management_system.repository.UserRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.stereotype.Service;


// // @Service
// // public class UserService {

// //     @Autowired
// //     private UserRepository userRepository;

// //     @Autowired
// //     private PasswordEncoder passwordEncoder;

// //     public User registerUser(User user) {
// //         user.setPassword(passwordEncoder.encode(user.getPassword()));
// //         return userRepository.save(user);
// //     }

// //     public User getUserByUsername(String username) {
// //         return userRepository.findByUsername(username);
// //         // .orElseThrow(() -> new RuntimeException("User not found"));
// //     }

// //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// //         User user = getUserByUsername(username);
// //         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
// //     }

// //     // private Collection<? extends GrantedAuthority> getAuthorities(User user) {
// //     //     return List.of(new SimpleGrantedAuthority(user.getRole()));
// //     // }
// // }
