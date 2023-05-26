DROP DATABASE QuanLyThuVien;
CREATE DATABASE QuanLyThuVien;
USE QuanLyThuVien;


CREATE TABLE NhanVien (
	MaNV INT PRIMARY KEY AUTO_INCREMENT,
	HoTenNV VARCHAR(50) NOT NULL,
	DiaChi VARCHAR(100) NOT NULL,
	SDT VARCHAR(50) NOT NULL UNIQUE,
	GioiTinh VARCHAR(10) ,
	NgaySinh DATE CHECK (NgaySinh <= sysdate()),
    ChucVu VARCHAR(50)  NOT NULL
);

CREATE TABLE Login (
MaTaiKhoan INT PRIMARY KEY AUTO_INCREMENT,
MaNV INT,
MatKhau VARCHAR(20) NOT NULL,
FOREIGN KEY (MANV) REFERENCES NhanVien(MANV)
);

CREATE TABLE DocGia(
	MaDG INT PRIMARY KEY AUTO_INCREMENT,
    TenDG VARCHAR(50),
    SDT VARCHAR(50) UNIQUE,
    DiaChi VARCHAR(100),
    NgaySinh DATE -- CHECK (NgaySinh <= sysdate())
);

CREATE TABLE TheDocGia (
	MaThe INT PRIMARY KEY AUTO_INCREMENT,
    NgayDK DATE,
    HanThe DATE,
    MaDG INT,
    PhiDK INT,
    FOREIGN KEY (MaDG) REFERENCES DocGia(MaDG)
);

CREATE TABLE NhaCungCap(
	MaNCC INT PRIMARY KEY AUTO_INCREMENT,
    TenNhaCC VARCHAR(100) NOT NULL,
    SDTNCC VARCHAR(50),
    DiaChiCC VARCHAR(100)
);

-- CREATE TABLE PhieuNhapLo(
-- 	MaPN INT PRIMARY KEY AUTO_INCREMENT,
--     TenNhaCC VARCHAR(100) NOT NULL,
--     NgayNhap DATE NOT NULL,
--     SDTNCC VARCHAR(50),
--     DiaChiCC VARCHAR(100),
--     TongTienNhap INT 
-- );

CREATE TABLE PhieuNhapLo (
	MaPN INT PRIMARY KEY AUTO_INCREMENT,
    NgayNhap DATE NOT NULL,
    TongTienNhap INT CHECK (TongTienNhap >0),
    MaNCC INT,
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap (MaNCC),
     MaNV INT,
    FOREIGN KEY (MaNV) REFERENCES nhanvien (MaNV)
);

CREATE TABLE ChiTietLo (
	MaDS INT PRIMARY KEY AUTO_INCREMENT,
    SoLuong INT,
    MaPN INT,
    FOREIGN KEY (MaPN) REFERENCES PhieuNhapLo(MaPN)
);

CREATE TABLE SACH(
	IDSACH INT PRIMARY KEY AUTO_INCREMENT,
    TenSach VARCHAR(100) NOT NULL ,
    TheLoai VARCHAR(100) NOT NULL,
    NamXB YEAR NOT NULL, 
    NXB VARCHAR(100) NOT NULL,
    TacGia VARCHAR(50) NOT NULL ,
    NgonNgu varchar(100),
    TinhTrang varchar(100),
    GiaSach INT NOT NULL,
     MaDS INT ,
    FOREIGN KEY (MaDS) REFERENCES ChiTietLo(MaDS)
);


CREATE TABLE PhieuMuon(
	MaPM INT PRIMARY KEY AUTO_INCREMENT,
    MaThe INT ,
    MaNV INT,
    NgayMuon DATE,
    NgayTra DATE,
    TinhTrang varchar (100) default "Chưa trả", 
    FOREIGN KEY (MaThe) REFERENCES thedocgia (MaThe),
    FOREIGN KEY (MaNV) REFERENCES nhanvien (MaNV)
);

CREATE TABLE ChiTietPhieuMuon (
	MaPM INT ,
    IDSACH INT ,
    PRIMARY KEY (MaPM, IDSACH),
    FOREIGN KEY (MaPM) REFERENCES PhieuMuon(MaPM),
    FOREIGN KEY (IDSACH) REFERENCES Sach(IDSACH)
);


-- -- **************************************
insert into NhanVien (MaNV, HoTenNV, DiaChi, SDT, GioiTinh, NgaySinh, ChucVu) values (1, 'Nguyễn Văn An', 'Quận 9-Thành phố Hồ Chí Minh' , '0963852741', 'Nam', '2000-5-25', 'admin');
insert into NhanVien (MaNV, HoTenNV, DiaChi, SDT, GioiTinh, NgaySinh, ChucVu) values (2, 'Trần Khánh Ngọc', 'Thủ Đức -Thành phố Hồ Chí Minh', '0978456123', 'Nữ', '2003-12-25', 'manage_stock');
insert into NhanVien (MaNV, HoTenNV, DiaChi, SDT, GioiTinh, NgaySinh, ChucVu) values (3, 'Định Văn Bảo', 'Quận 11- Thành phố Hồ Chí Minh', '0365412789', 'Nam', '2003-7-10', 'staff');
-- -- **************************************
insert into Login (MaTaiKhoan, MatKhau, MaNV) VALUE(1, 111, 1);
insert into Login (MaTaiKhoan, MatKhau, MaNV) VALUE(2, 222, 2);
insert into Login (MaTaiKhoan, MatKhau, MaNV) VALUE(3, 333, 3);
-- *************************************************
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (1, 'Atalanta Huddle', '0974152632', '2957 Ridge Oak Court', '2003-12-9');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (2, 'Tedie Ellick', '0385274196', '51 Cody Road', '2001-03-20');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (3, 'Garry Baford', '0325874691', '7 Main Way', '1999-02-10');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (4, 'Zebulen Cutford', '0332587419', '498 Ludington Park', '2004-11-9');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (5, 'Otis Bazoche', '0969327915', '74 Myrtle Point', '1998-01-12');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (6, 'Trstram Leven', '0382597168', '158 Waubesa Plaza', '1976-02-03');
-- *************************************************
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (1, '2022-2-5', '2023-2-5',1,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (2, '2022-6-3', '2023-6-3',2,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (3, '2023-4-3', '2024-4-3',3,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (4, '2023-5-1', '2025-5-1',4,200000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (5, '2023-5-4', '2024-5-4',5,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (6, '2023-5-4', '2025-3-4',6,200000);

-- *************** *****************
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap)values (1, 'Công Ty TNHH Đăng Nguyên','2022-04-05', '0987456123', '187 Giảng Võ, Q. Đống Đa, Hà Nội', 180000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (2, 'Công Ty Cổ Phần Sách Mcbooks', '2022-04-20','0963852741', '14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh (TPHCM)',290000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (3, 'Nhà Sách Trực Tuyến BOOKBUY.VN', '2023-01-10','0321456987', '147 Pasteur, P. 6, Q. 3, Tp. Hồ Chí Minh (TPHCM)',510000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (4, 'Công Ty Cổ Phần Sách & Thiết Bị Trường Học','2023-02-26','0354698127', '289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội',360000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (5, 'TNHH Văn Hóa Việt Long','2023-04-15', '0396857412', 'Lô 34E, Khu Đấu Giá 3ha, P. Phúc Diễm, Q. Bắc Từ Liêm, Hà Nội',370000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (6, 'Công Ty Cổ Phần Phát Hành Sách Tp. HCM',  '2023-03-26','02838225798', 'Công Ty Cổ Phần Phát Hành Sách Tp. HCM',300000);
-- insert into PhieuNhapLo (MaPN, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (7, 'Nhà Sách Ngoại Văn BOA(Books of Awesome)', '2023-04-20', '0909892312', 'Phòng C26, Lầu 2, 42 Trần Cao Vân, Q.3 (Hồ Con Rùa), Tp. Hồ Chí Minh (TPHCM)',165000);
-- -- **************************************
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (1, 'Công Ty TNHH Đăng Nguyên', '0987456123', '187 Giảng Võ, Q. Đống Đa, Hà Nội');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (2, 'Công Ty Cổ Phần Sách Mcbooks', '0963852741', '14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh (TPHCM)');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (3, 'Nhà Sách Trực Tuyến BOOKBUY.VN', '0321456987', '147 Pasteur, P. 6, Q. 3, Tp. Hồ Chí Minh (TPHCM)');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (4, 'Công Ty Cổ Phần Sách & Thiết Bị Trường Học','0354698127', '289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (5, 'TNHH Văn Hóa Việt Long', '0396857412', 'Lô 34E, Khu Đấu Giá 3ha, P. Phúc Diễm, Q. Bắc Từ Liêm, Hà Nội');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (6, 'Công Ty Cổ Phần Phát Hành Sách Tp. HCM', '02838225798', 'Công Ty Cổ Phần Phát Hành Sách Tp. HCM');
insert into NhaCungCap (MaNCC, TenNhacc, SdtNCC, DiaChiCC) values (7, 'Nhà Sách Ngoại Văn BOA(Books of Awesome)', '0909892312', 'Phòng C26, Lầu 2, 42 Trần Cao Vân, Q.3 (Hồ Con Rùa), Tp. Hồ Chí Minh (TPHCM)');

-- -- **************************************
insert into PhieuNhapLo (MaPN,  NgayNhap, TongTienNhap, MaNCC,MaNV) values (1, '2022-04-05', 180000, 1, 2);
insert into PhieuNhapLo (MaPN, NgayNhap, TongTienNhap, MaNCC,MaNV) values (2, '2022-04-20', 290000,2, 2);
insert into PhieuNhapLo (MaPN,  NgayNhap, TongTienNhap, MaNCC,MaNV) values (3,'2023-01-10', 510000,3, 2);
insert into PhieuNhapLo (MaPN,  NgayNhap, TongTienNhap ,MaNCC,MaNV) values (4, '2023-02-26', 360000,4, 2);
insert into PhieuNhapLo (MaPN, NgayNhap, TongTienNhap ,MaNCC,MaNV) values (5, '2023-04-15', 370000,5, 2);
insert into PhieuNhapLo (MaPN,  NgayNhap, TongTienNhap ,MaNCC,MaNV) values (6, '2023-03-26', 300000,7, 2);
insert into PhieuNhapLo (MaPN, NgayNhap, TongTienNhap ,MaNCC,MaNV) values (7, '2023-04-20', 165000,6, 2);
-- *************************************************
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(1, 3, 1);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(2, 2, 2);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(3, 3, 2);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(4, 3,3);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(5, 3, 3);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(6, 4, 4);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(7, 3, 4);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(8, 4, 5);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(9, 4, 5);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(10, 2 , 5);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(11, 3, 6);
insert into ChiTietLo (MaDS, SoLuong,MaPN) VALUE(12, 3, 7);
-- ************************************** * 	28		26			29		
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values
				(1, 'Tuổi 20 yêu dấu', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt', 'Chưa Mượn', 60000,1);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values
				(2, 'Tuổi 20 yêu dấu', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt', 'Đã Mượn', 60000,1);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(3, 'Tuổi 20 yêu dấu', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt', 'Chưa Mượn', 60000,1);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(4, 'Đắc nhân tâm', 'Kinh doanh', 1970, 'NXB Trẻ', 'Dale Carnegie',  'Tiếng Việt', 'Chưa Mượn', 40000,2);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(5, 'Đắc nhân tâm', 'Kinh doanh', 1970, 'NXB Trẻ', 'Dale Carnegie',  'Tiếng Việt', 'Đã Mượn', 40000,2);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(6, 'Sức mạnh của những suy nghĩ tích cực', 'Tâm lý học', 2006, 'NXB Thế giới', 'Norman Vincent Peale',  'Tiếng Việt', 'Đã Mượn', 70000,3);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(7, 'Sức mạnh của những suy nghĩ tích cực', 'Tâm lý học', 2006, 'NXB Thế giới', 'Norman Vincent Peale',  'Tiếng Việt', 'Đã Mượn', 70000,3);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(8, 'Sức mạnh của những suy nghĩ tích cực', 'Tâm lý học', 2006, 'NXB Thế giới', 'Norman Vincent Peale',  'Tiếng Việt', 'Chưa Mượn', 70000,3);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(9, 'Cơ hội của Chúa', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt','Chưa Mượn', 50000,4);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(10, 'Cơ hội của Chúa', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt','Đã Mượn', 50000,4);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(11, 'Cơ hội của Chúa', 'Tiểu thuyết', 1999, 'NXB Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt', 'Chưa Mượn', 50000,4);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(12, 'Mắt biếc', 'Tiểu thuyết', 1990, 'NXB Trẻ', 'Nguyễn Nhật Ánh',  'Tiếng Việt', 'Chưa Mượn', 120000,5);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(13, 'Mắt biếc', 'Tiểu thuyết', 1990, 'NXB Trẻ', 'Nguyễn Nhật Ánh',  'Tiếng Việt', 'Chưa Mượn', 120000,5);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(14, 'Mắt biếc', 'Tiểu thuyết', 1990, 'NXB Trẻ', 'Nguyễn Nhật Ánh',  'Tiếng Việt','Chưa Mượn', 120000,5);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(15, 'Sherlock Holmes: Cuộc phiêu lưu của huyền thoại Baskerville', 'Trinh thám', 1902, 'NXB Văn học', 'Arthur Conan Doyle',  'Tiếng Việt','Đã Mượn', 75000,6);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(16, 'Sherlock Holmes: Cuộc phiêu lưu của huyền thoại Baskerville', 'Trinh thám', 1902, 'NXB Văn học', 'Arthur Conan Doyle',  'Tiếng Việt','Đã Mượn', 75000,6);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(17, 'Sherlock Holmes: Cuộc phiêu lưu của huyền thoại Baskerville', 'Trinh thám', 1902, 'NXB Văn học', 'Arthur Conan Doyle',  'Tiếng Việt', 'Chưa Mượn', 75000,6);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(18, 'Sherlock Holmes: Cuộc phiêu lưu của huyền thoại Baskerville', 'Trinh thám', 1902, 'NXB Văn học', 'Arthur Conan Doyle',  'Tiếng Việt', 'Đã Mượn', 75000,6);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(19, 'Vòng quanh thế giới', 'Trinh thám', 2022, 'NXB Kim Đồng', 'Hoài Nam',  'Tiếng Việt', 'Chưa Mượn', 20000,7);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values  
				(20,  'Vòng quanh thế giới', 'Trinh thám', 2022, 'NXB Kim Đồng', 'Hoài Nam', 'Tiếng Việt', 'Chưa Mượn', 20000,7);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(21, 'Vòng quanh thế giới', 'Trinh thám', 2022, 'NXB Kim Đồng', 'Hoài Nam',  'Tiếng Việt', 'Chưa Mượn', 20000,7);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(22,  'Những thiếu nhi bên Bác ngày ấy', 'Lịch sử', 1923, 'NXB Kim Đồng', 'Kiều Mai Sơn',  'Tiếng Việt', 'Chưa Mượn', 40000, 8);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(23, 'Những thiếu nhi bên Bác ngày ấy', 'Lịch sử', 1923, 'NXB Kim Đồng', 'Kiều Mai Sơn',  'Tiếng Việt', 'Chưa Mượn', 40000, 8);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(24, 'Những thiếu nhi bên Bác ngày ấy', 'Lịch sử', 1923, 'NXB Kim Đồng', 'Kiều Mai Sơn',  'Tiếng Việt', 'Chưa Mượn', 40000, 8);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(25, 'Những thiếu nhi bên Bác ngày ấy', 'Lịch sử', 1923, 'NXB Kim Đồng', 'Kiều Mai Sơn',  'Tiếng Việt', 'Chưa Mượn', 40000, 8);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(26, 'Trân lý trí, trọng cảm xúc', 'Kỹ năng sống', 2022, 'NXB Kim Đồng', 'Kiếm Thánh Miêu',  'Tiếng Việt', 'Đã Mượn', 30000, 9);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(27, 'Trân lý trí, trọng cảm xúc', 'Kỹ năng sống', 2022, 'NXB Kim Đồng', 'Kiếm Thánh Miêu',  'Tiếng Việt', 'Đã Mượn', 30000, 9);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(28, 'Trân lý trí, trọng cảm xúc', 'Kỹ năng sống', 2022, 'NXB Kim Đồng', 'Kiếm Thánh Miêu',  'Tiếng Việt', 'Đã Mượn', 30000, 9);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(29, 'Trân lý trí, trọng cảm xúc', 'Kỹ năng sống', 2022, 'NXB Kim Đồng', 'Kiếm Thánh Miêu',  'Tiếng Việt', 'Đã Mượn', 30000, 9);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values  
				(30, 'Thần thoại hy lạp', 'Tiểu thuyết', 2021, 'NXB Kim Đồng', 'Nguyễn Văn Khỏa',  'Tiếng Việt', 'Chưa Mượn', 45000,10);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(31, 'Thần thoại hy lạp', 'Tiểu thuyết', 2021, 'NXB Kim Đồng', 'Nguyễn Văn Khỏa',  'Tiếng Việt', 'Chưa Mượn', 45000,10);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(32, 'Thơ và đời', 'Thơ', 2019, 'NXB Văn Học', 'Xuân Diệu',  'Tiếng Việt', 'Chưa Mượn', 100000, 11);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(33, 'Thơ và đời', 'Thơ', 2019, 'NXB Văn Học', 'Xuân Diệu',  'Tiếng Việt', 'Chưa Mượn', 100000, 11);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(34, 'Thơ và đời', 'Thơ', 2019, 'NXB Văn Học', 'Xuân Diệu',  'Tiếng Việt', 'Chưa Mượn', 100000, 11);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(35, 'Hoàng tử bé', 'Truyện', 1902, 'Hội Nhà Văn', 'Antoine De Saint-Exupery',  'Tiếng Việt','Chưa Mượn', 55000,12);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(36, 'Hoàng tử bé', 'Truyện', 1902, 'Hội Nhà Văn', 'Antoine De Saint-Exupery',  'Tiếng Việt','Chưa Mượn', 55000,12);
insert into Sach (IDSACH, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values 
				(37, 'Hoàng tử bé', 'Truyện', 1902, 'Hội Nhà Văn', 'Antoine De Saint-Exupery',  'Tiếng Việt','Chưa Mượn', 55000,12);
 -- **************************************
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV ) values (1, 1, '2023-1-10', '2023-2-10', 1);
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV) values (2, 2, '2023-1-10', '2023-2-10', 1);
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV) values (3, 3, '2023-02-20', '2023-4-20', 1);
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV) values (4, 4, '2023-04-05', '2023-5-05', 1);
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV) values (5, 5, '2023-04-15', '2023-06-15', 1);
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra,MaNV) values (6, 6, '2023-05-02', '2023-06-02', 1);
-- **************************************
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (1, 27);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (1, 5);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (2, 6);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (2, 28);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (3, 18);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (4, 26);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (4, 7);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (4, 16);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (4, 29);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (5, 2);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (6, 15);
insert into ChiTietPhieuMuon (MaPM, IDSACH) values (6, 10);