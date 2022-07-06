package com.example.khoj.Fragments.Hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoj.R;
import com.example.khoj.Services.FetchLocation;
import com.example.khoj.Services.ImageUploader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class SpecializationFragment extends Fragment {
    TextView Specialization, Info;
    String specialization;
    String ImageURL, description, checkData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specialization_layout,container,false);
        Info = view.findViewById(R.id.infotext);
        Specialization = view.findViewById(R.id.specializationid);

        specialization = getArguments().getString("specialization");

        if(specialization!=null) {
            Specialization.setText(specialization);
        }

        if(specialization.equals("Allergy and Immunology")){
           description = "Specialists in allergy and immunology work with both adult and pediatric patients suffering from allergies and diseases of the respiratory tract or immune system.\n" +
                   "        They may help patients suffering from common diseases such as asthma, food and drug allergies, immune deficiencies, and diseases of the lung.\n" +
                   "        Specialists in allergy and immunology can pursue opportunities in research, education, or clinical practice.\n" +
                   "   ";

        }

        else if(specialization.equals("Anesthesiology")){
            description = " Anesthesiology is the branch of medicine dedicated to pain relief for patients before, during, and after surgery.\n" +
                    "        The American Board of Anesthesiology outlines the following subspecialties within the field in the following areas of care:\n" +
                    "\n" +
                    "        • Critical care medicine\n" +
                    "        • Hospice and palliative care\n" +
                    "        • Pain medicine\n" +
                    "        • Pediatric anesthesiology\n" +
                    "        • Sleep medicine";

        }

        else if(specialization.equals("Dermatology")){
            description = "Dermatologists are physicians who treat adult and pediatric patients with disorders of the skin, hair, nails, and adjacent mucous membranes.\n" +
                    "        They diagnose everything from skin cancer, tumors, inflammatory diseases of the skin, and infectious diseases.\n" +
                    "        They also perform skin biopsies and dermatological surgical procedures.\n" +
                    "\n" +
                    "        Subspecialties within the dermatology field include the following:\n" +
                    "\n" +
                    "        • Dermatopathology\n" +
                    "        • Pediatric dermatology\n" +
                    "        • Procedural dermatology  ";

        }
        else if(specialization.equals("Diagnostic radiology")){
            description = " Physicians specializing in diagnostic radiology are trained to diagnose illnesses in patients through the use of x-rays, radioactive substances, sound waves in ultrasounds, or the body’s natural magnetism in magnetic resonance images (MRIs).\n" +
                    "\n" +
                    "        They can also pursue a subspecialty in the following areas:\n" +
                    "\n" +
                    "        • Abdominal radiology\n" +
                    "        • Breast imaging\n" +
                    "        • Cardiothoracic radiology\n" +
                    "        • Cardiovascular radiology\n" +
                    "        • Chest radiology\n" +
                    "        • Emergency radiology\n" +
                    "        • Endovascular surgical neuroradiology\n" +
                    "        • Gastrointestinal radiology\n" +
                    "        • Genitourinary radiology\n" +
                    "        • Head and neck radiology\n" +
                    "        • Interventional radiology\n" +
                    "        • Musculoskeletal radiology\n" +
                    "        • Neuroradiology\n" +
                    "        • Nuclear radiology\n" +
                    "        • Pediatric radiology\n" +
                    "        • Radiation oncology\n" +
                    "        • Vascular and interventional radiology ";

        }
        else if(specialization.equals("Emergency medicine")){
            description = "Physicians specializing in emergency medicine provide care for adult and pediatric patients in emergency situations.\n" +
                    "        These specialists provide immediate decision making and action to save lives and prevent further injury.\n" +
                    "        They help patients in the pre-hospital setting by directing emergency medical technicians and assisting patients once they arrive in the emergency department.\n" +
                    "\n" +
                    "        Emergency medicine is also home to several subspecialties, including the following:\n" +
                    "\n" +
                    "        • Anesthesiology critical care medicine\n" +
                    "        • Emergency medical services\n" +
                    "        • Hospice and palliative medicine\n" +
                    "        • Internal medicine / Critical care medicine\n" +
                    "        • Medical toxicology\n" +
                    "        • Pain medicine\n" +
                    "        • Pediatric emergency medicine\n" +
                    "        • Sports medicine\n" +
                    "        • Undersea and hyperbaric medicine ";

        }
        else if(specialization.equals("Family medicine")){
            description = "While many medical specialties focus on a certain function of the body or particular organ, family medicine focuses on integrated care and treating the patient as a whole.\n" +
                    "        Physicians who specialize in family medicine treat patients of all ages.\n" +
                    "        They are extensively trained to provide comprehensive health care and treat most ailments.\n" +
                    "\n" +
                    "        There are family medicine subspecialties as well, including the following:\n" +
                    "\n" +
                    "        • Adolescent medicine\n" +
                    "        • Geriatric medicine\n" +
                    "        • Hospice and palliative medicine\n" +
                    "        • Pain medicine\n" +
                    "        • Sleep medicine\n" +
                    "        • Sports medicine ";

        }
        else if(specialization.equals("Internal medicine")){
            description = "An internist is a physician who treats diseases of the heart, blood, kidneys, joints, digestive, respiratory, and vascular systems of adolescent, adult, and elderly patients.\n" +
                    "        These physicians provide long-term and comprehensive care in hospitals and offices.\n" +
                    "        Because they undergo primary care training on internal medicine, these physicians also address disease prevention, wellness, substance abuse, and mental health.\n" +
                    "\n" +
                    "        Internal medicine subspecialties including the following:\n" +
                    "\n" +
                    "        • Advanced heart failure and transplant cardiology\n" +
                    "        • Cardiovascular disease\n" +
                    "        • Clinical cardiac electrophysiology\n" +
                    "        • Critical care medicine\n" +
                    "        • Endocrinology, diabetes, and metabolism\n" +
                    "        • Gastroenterology\n" +
                    "        • Geriatric medicine\n" +
                    "        • Hematology\n" +
                    "        • Hematology and oncology\n" +
                    "        • Infectious disease\n" +
                    "        • Internal medicine\n" +
                    "        • Interventional cardiology\n" +
                    "        • Nephrology\n" +
                    "        • Oncology\n" +
                    "        • Pediatric internal medicine\n" +
                    "        • Pulmonary disease\n" +
                    "        • Pulmonary disease and critical care medicine\n" +
                    "        • Rheumatology\n" +
                    "        • Sleep medicine\n" +
                    "        • Sports medicine\n" +
                    "        • Transplant hepatology ";

        }
        else if(specialization.equals("Medical genetics")){
            description = " A medical geneticist is a physician who treats hereditary disorders and diagnoses diseases that are caused by genetic defects. Medical geneticists may provide patients with therapeutic interventions and specialized counseling.\n" +
                    "        They also educate patients and their families on their diagnoses and how to cope with their genetic disorder.\n" +
                    "        Medical geneticists conduct cytogenetic, radiologic, and biochemical testing and scientific research in the field.\n" +
                    "\n" +
                    "        Medical geneticists house several subspecialties within the field, including the following:\n" +
                    "\n" +
                    "        • Biochemical genetics\n" +
                    "        • Clinical cytogenetics\n" +
                    "        • Clinical genetics\n" +
                    "        • Molecular genetic pathology";

        }
        else if(specialization.equals("Neurology")){
            description = " Neurology is the specialty within the medical field pertaining to nerves and the nervous system.\n" +
                    "        Neurologists diagnose and treat diseases of the brain, spinal cord, peripheral nerves, muscles, autonomic nervous system, and blood vessels.\n" +
                    "        Much of neurology is consultative, as neurologists treat patients suffering from strokes, Alzheimer’s disease, seizure disorders, and spinal cord disorders.\n" +
                    "\n" +
                    "        Neurology offers several subspecialties, including the following:\n" +
                    "\n" +
                    "        • Brain injury medicine\n" +
                    "        • Child neurology\n" +
                    "        • Clinical neurophysiology\n" +
                    "        • Endovascular surgical neuroradiology\n" +
                    "        • Hospice and palliative medicine\n" +
                    "        • Neurodevelopmental disabilities\n" +
                    "        • Neuromuscular medicine\n" +
                    "        • Pain medicine\n" +
                    "        • Sleep medicine\n" +
                    "        • Vascular neurology";

        }
        else if(specialization.equals("Nuclear medicine")){
            description = " Physicians who practice nuclear medicine are called nuclear radiologists or nuclear medicine radiologists.\n" +
                    "        They use radioactive materials to diagnose and treat diseases. Utilizing techniques such as scintigraphy, these physicians analyze images of the body's organs to visualize certain diseases.\n" +
                    "        They may also use radiopharmaceuticals to treat hyperthyroidism, thyroid cancer, tumors, and bone cancer.\n" +
                    "  ";

        }
        else if(specialization.equals("Obstetrics and gynecology")){
            description = "Obstetrician/gynecologists (OB/GYNs) care for the female reproductive system and associated disorders.\n" +
                    "        This field of medicine encompasses a wide array of care, including the care of pregnant women, gynecologic care, oncology, surgery, and primary health care for women.\n" +
                    "        Several subspecialties within obstetrics and gynecology include the following:\n" +
                    "\n" +
                    "        • Female pelvic medicine and reconstructive surgery\n" +
                    "        • Gynecologic oncology\n" +
                    "        • Maternal-fetal medicine\n" +
                    "        • Reproductive endocrinologists and infertility\n" +
                    "   ";

        }
        else if(specialization.equals("Ophthalmology")){
            description = " Physicians specializing in ophthalmology develop comprehensive medical and surgical care of the eyes.\n" +
                    "        Ophthalmologists diagnose and treat vision problems.\n" +
                    "        They may treat strabismus, diabetic retinopathy, or perform surgeries on cataracts or corneal transplantation.\n" +
                    "\n" +
                    "        There are several subspecialties within the ophthalmology field, including the following:\n" +
                    "\n" +
                    "        • Anterior segment/cornea ophthalmology\n" +
                    "        • Glaucoma ophthalmology\n" +
                    "        • Neuro-ophthalmology\n" +
                    "        • Ocular oncology\n" +
                    "        • Oculoplastics/orbit\n" +
                    "        • Ophthalmic Plastic &amp; Reconstructive Surgery\n" +
                    "        • Retina/uveitis\n" +
                    "        • Strabismus/pediatric ophthalmology\n" +
                    "  ";

        }
        else if(specialization.equals("Pathology")){
            description = "  A physician specializing in pathology studies the causes and nature of diseases.\n" +
                    "        Through microscopic examination and clinical lab tests, pathologists work to diagnose, monitor, and treat diseases.\n" +
                    "        They examine tissues, cells, and body fluids, applying biological, chemical, and physical sciences within the laboratory.\n" +
                    "        They may examine tissues to determine whether an organ transplant is needed, or they may examine the blood of a pregnant woman to ensure the health of the fetus.\n" +
                    "\n" +
                    "        Pathology umbrellas several areas of subspecialty within the field, including the following:\n" +
                    "\n" +
                    "        • Anatomical pathology\n" +
                    "        • Blood banking and transfusion medicine\n" +
                    "        • Chemical pathology\n" +
                    "        • Clinical pathology\n" +
                    "        • Cytopathology\n" +
                    "        • Forensic pathology\n" +
                    "        • Genetic pathology\n" +
                    "        • Hematology\n" +
                    "        • Immunopathology\n" +
                    "        • Medical microbiology\n" +
                    "        • Molecular pathology\n" +
                    "        • Neuropathology\n" +
                    "        • Pediatric pathology ";

        }
        else if(specialization.equals("Pediatrics")){
            description = "Physicians specializing in pediatrics work to diagnose and treat patients from infancy through adolescence.\n" +
                    "        Pediatricians practice preventative medicine and also diagnose common childhood diseases, such as asthma, allergies, and croup.\n" +
                    "\n" +
                    "        They may work as a primary care pediatrician treating an array of ailments, or narrowing their scope of practice in one of the following subspecialties:\n" +
                    "\n" +
                    "        • Adolescent medicine\n" +
                    "        • Child abuse pediatrics\n" +
                    "        • Developmental-behavioral pediatrics\n" +
                    "        • Neonatal-perinatal medicine\n" +
                    "        • Pediatric cardiology\n" +
                    "        • Pediatric critical care medicine\n" +
                    "        • Pediatric endocrinology\n" +
                    "        • Pediatric gastroenterology\n" +
                    "        • Pediatric hematology-oncology\n" +
                    "        • Pediatric infectious diseases\n" +
                    "        • Pediatric nephrology\n" +
                    "        • Pediatric pulmonology\n" +
                    "        • Pediatric rheumatology\n" +
                    "        • Pediatric sports medicine\n" +
                    "        • Pediatric transplant hepatology";

        }
        else if(specialization.equals("Physical medicine and Rehabilitation")){
            description = "Physicians specializing in physical medicine and rehabilitation work to help patients with disabilities of the brain, spinal cord, nerves, bones, joints, ligaments, muscles, and tendons.\n" +
                    "        Physiatrists work with patients of all ages and design care plans for conditions, such as spinal cord or brain injury, stroke, multiple sclerosis, and musculoskeletal and pediatric rehabilitation.\n" +
                    "        Unlike many other medical specialties, physiatrists work to improve patient quality of life, rather than seek medical cures.\n" +
                    "\n" +
                    "        Subspecialties in this field include the following:\n" +
                    "\n" +
                    "        • Brain injury medicine\n" +
                    "        • Hospice and palliative medicine\n" +
                    "        • Neuromuscular medicine\n" +
                    "        • Pain medicine\n" +
                    "        • Pediatric rehabilitation medicine\n" +
                    "        • Spinal cord injury medicine\n" +
                    "        • Sports medicine";

        }
        else if(specialization.equals("Preventive medicine")){
            description = " Physicians specializing in preventative medicine work to prevent disease by promoting patient health and well-being.\n" +
                    "        Their expertise goes far beyond preventative practices in clinical medicine, covering elements of biostatistics, epidemiology, environmental and occupational medicine, and even the evaluation and management of health services and healthcare organizations.\n" +
                    "        The field combines interdisciplinary elements of medical, social, economic, and behavioral sciences to understand the causes of disease and injury in population groups.\n" +
                    "\n" +
                    "        Subspecialties within preventative medicine include the following:\n" +
                    "\n" +
                    "        • Aerospace medicine\n" +
                    "        • Medical toxicology\n" +
                    "        • Occupational medicine\n" +
                    "        • Public health medicine";

        }
        else if(specialization.equals("Psychiatry")){
            description = "Physicians specializing in psychiatry devote their careers to mental health and its associated mental and physical ramifications.\n" +
                    "        Understanding the connections between genetics, emotion, and mental illness, is important while psychiatrists also conduct medical laboratory and psychological tests to diagnose and treat patients.\n" +
                    "\n" +
                    "        Subspecialties within psychiatry include the following:\n" +
                    "\n" +
                    "        • Addiction psychiatry\n" +
                    "        • Administrative psychiatry\n" +
                    "        • Child and adolescent psychiatry\n" +
                    "        • Community psychiatry\n" +
                    "        • Consultation/liaison psychiatry\n" +
                    "        • Emergency psychiatry\n" +
                    "        • Forensic psychiatry\n" +
                    "        • Geriatric psychiatry\n" +
                    "        • Mental retardation psychiatry\n" +
                    "        • Military psychiatry\n" +
                    "        • Pain medicine\n" +
                    "        • Psychiatric research\n" +
                    "        • Psychosomatic medicine";

        }
        else if(specialization.equals("Radiation oncology")){
            description = " Physicians specializing in radiation oncology treat cancer with the use of high-energy radiation therapy.\n" +
                    "        By targeting radiation doses in small areas of the body, radiation oncologists damage the DNA of cancer cells, preventing further growth.\n" +
                    "        Radiation oncologists work with cancer patients, prescribing and implementing treatment plans while monitoring their progress throughout.\n" +
                    "\n" +
                    "        Radiation oncology houses a few subspecialties, including the following:\n" +
                    "\n" +
                    "        • Hospice and palliative medicine\n" +
                    "        • Pain medicine";

        }
        else if(specialization.equals("Surgery")){
            description = " Physicians specializing in surgery can choose to become general surgeons or pursue a subspecialty in a specific area of the body, type of patient, or type of surgery. General surgeons provide a wide variety of life-saving surgeries, such as appendectomies and splenectomies. They receive broad training on human anatomy, physiology, intensive care, and wound healing.\n" +
                    "\n" +
                    "        The Association of American Medical Colleges and American College of Surgeons outline a number of surgical subspecialties and areas of practice, including the following:\n" +
                    "\n" +
                    "        • Colon and rectal surgery\n" +
                    "        • General surgery\n" +
                    "            o Surgical critical care\n" +
                    "        • Gynecologic oncology\n" +
                    "        • Plastic surgery\n" +
                    "            o Craniofacial surgery\n" +
                    "            o Hand surgery\n" +
                    "        • Neurological surgery\n" +
                    "            o Endovascular surgical neuroradiology\n" +
                    "        • Ophthalmic surgery\n" +
                    "        • Oral and maxillofacial surgery\n" +
                    "        • Orthopaedic surgery\n" +
                    "            o Adult reconstructive orthopaedics\n" +
                    "            o Foot and ankle orthopaedics\n" +
                    "            o Musculoskeletal oncology\n" +
                    "            o Orthopaedic sports medicine\n" +
                    "            o Orthopaedic surgery of the spine\n" +
                    "            o Orthopaedic trauma\n" +
                    "            o Pediatric orthopaedics\n" +
                    "        • Otolaryngology\n" +
                    "            o Pediatric otolaryngology\n" +
                    "        • Otology neurotology\n" +
                    "        • Pediatric surgery\n" +
                    "            o Neonatal\n" +
                    "            o Prenatal\n" +
                    "            o Trauma\n" +
                    "            o Pediatric oncology\n" +
                    "        • Surgical Intensivists, specializing in critical care patients\n" +
                    "        • Thoracic Surgery\n" +
                    "            o Congenital cardiac surgery\n" +
                    "            o Thoracic surgery-integrated\n" +
                    "        • Vascular surgery";

        }
        else if(specialization.equals("Urology")){
            description = " Urology is the health care segment that cares for the male and female urinary tract, including kidneys, ureters, bladder, and urethra.\n" +
                    "        It also deals with the male sex organs. Urologists have knowledge of surgery, internal medicine, pediatrics, gynecology, and more.\n" +
                    "\n" +
                    "        Within urology, there are several areas of subspecialty, including the following:\n" +
                    "\n" +
                    "        • Pediatric urology\n" +
                    "        • Urologic oncology\n" +
                    "        • Renal transplant\n" +
                    "        • Male infertility\n" +
                    "        • Calculi\n" +
                    "        • Female urology\n" +
                    "        • Neurourology";

        }
        else{
            description="";
        }


        Specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SpecializationDescription.class);
                intent.putExtra("specialization",specialization);
                intent.putExtra("image",ImageURL);
                intent.putExtra("description",description);
                startActivity(intent);
            }
        });

        return view;
    }
}
