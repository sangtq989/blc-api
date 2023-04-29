CREATE TABLE public.form
(
    id            bigserial NOT NULL,
    form_desc     varchar(255) NULL,
    form_key      varchar(255) NULL,
    form_sub_type varchar(255) NULL,
    form_title    varchar(255) NULL,
    CONSTRAINT form_pkey PRIMARY KEY (id)
);

CREATE TABLE public.form_field
(
    id              bigserial NOT NULL,
    field_data_type varchar(255) NULL,
    field_desc      varchar(255) NULL,
    field_title     varchar(255) NULL,
    form_field_key  varchar(255) NULL,
    form_id         int8 NULL,
    CONSTRAINT form_field_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users_info
(
    id                   bigserial NOT NULL,
    address              varchar(255) NULL,
    block_chain_address  varchar(255) NULL,
    date_of_birth        date NULL,
    email                varchar(255) NULL,
    first_name           varchar(255) NULL,
    gender               varchar(255) NULL,
    last_name            varchar(255) NULL,
    personal_description varchar(255) NULL,
    phone_number         varchar(255) NULL,
    "password"           varchar(255) NULL,
    CONSTRAINT users_info_pkey PRIMARY KEY (id)
);

CREATE TABLE public.metadata
(
    id                 bigserial NOT NULL,
    field_value        varchar(255) NULL,
    form_field_key     varchar(255) NULL,
    form_key           varchar(255) NULL,
    object_id          uuid NULL,
    user_block_address varchar(255) NULL,
    user_id            int8 NULL,
    CONSTRAINT metadata_pkey PRIMARY KEY (id)
);

ALTER TABLE public.metadata
    ADD CONSTRAINT fkm35jlx5wgsl4qvf9hra4vcjtl FOREIGN KEY (user_id) REFERENCES public.users_info (id);

ALTER TABLE public.form_field
    ADD CONSTRAINT fkbgdx9knhcvbjyxlq9iv0x04es FOREIGN KEY (form_id) REFERENCES public.form (id);

INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (1, NULL, 'SPECIALTY', NULL, 'Thông tin chuyên ngành');
INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (2, NULL, 'EXPERIENCE', NULL, 'Kinh nghiệm làm việc');
INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (3, NULL, 'CERT_EDU', 'Education', 'Chứng chỉ, bằng cấp');
INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (4, NULL, 'CERT_LICENSES', 'Liscenses & Certification ', 'Chứng chỉ, bằng cấp');
INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (5, NULL, 'CERT_COURSE', 'Courses', 'Chứng chỉ, bằng cấp');
INSERT INTO public.form
    (id, form_desc, form_key, form_sub_type, form_title)
VALUES (6, NULL, 'CERT_AWARD', 'Honors & Awards', 'Chứng chỉ, bằng cấp');


INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (1, 'String', NULL, 'Chuyên môn', 'specialities', 1);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (2, 'Integer', NULL, 'Năm kinh nghiệm', 'yearOfExp', 1);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (3, 'String', NULL, 'Kỹ năng', 'skills', 1);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (4, 'String', NULL, 'Ngôn ngữ', 'languages', 1);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (5, 'String', NULL, 'Vị trí', 'positionName', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (6, 'String', NULL, 'Chức vụ', 'employeeType', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (7, 'String', NULL, 'Tên công ty', 'companyName', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (8, 'String', NULL, 'Địa chỉ', 'companyAddress', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (9, 'Boolean', NULL, 'Công việc hiện tại', 'isCurrentJob', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (10, 'LocalDate', NULL, 'Thời gian bắt đầu', 'startDate', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (11, 'LocalDate', NULL, 'Thời gian kết thúc', 'endDate', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (12, 'String', NULL, 'Mô tả công việc', 'jobDesc', 2);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (13, 'String', NULL, 'Tên trường', 'uniName', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (14, 'String', NULL, 'Bằng cấp', 'degree', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (15, 'String', NULL, 'Chuyên ngành', 'major', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (16, 'Float', NULL, 'Gpa', 'startTime', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (17, 'LocalDate', NULL, 'Thời gian bắt đầu', 'endTime', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (18, 'LocalDate', NULL, 'Thời gian kết thúc', 'gpa', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (19, 'String', NULL, 'Mô tả', 'description', 3);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (20, 'String', NULL, 'Tên chứng chỉ', 'licensesName', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (21, 'String', NULL, 'Tổ chức phát hành', 'publisher', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (22, 'LocalDate', NULL, 'Thời gian phát hành', 'publishFrom', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (23, 'LocalDate', NULL, 'Thời gian hết hạn', 'publishTo', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (24, 'String', NULL, 'Mã chứng chỉ', 'licensesCode', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (25, 'String', NULL, 'Url chứng chỉ', 'licensesUrl', 4);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (26, 'String', NULL, 'Tên', 'courseName', 5);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (27, 'String', NULL, 'Tổ chức', 'courseProvider', 5);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (28, 'String', NULL, 'Tên', 'awardName', 6);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (29, 'String', NULL, 'Tổ chức', 'awardOrg', 6);
INSERT INTO public.form_field
    (id, field_data_type, field_desc, field_title, form_field_key, form_id)
VALUES (30, 'LocalDate', NULL, 'Thời gian phát hành', 'publishDate', 6);